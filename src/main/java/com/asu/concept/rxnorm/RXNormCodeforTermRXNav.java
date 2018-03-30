package com.asu.concept.rxnorm;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.asu.conceptcode.excel.ReadFromExcel;
import com.asu.conceptcode.excel.WriteToExcel;
import com.asu.conceptcode.util.CandidateRelatedUtil;
import com.asu.conceptcode.util.CandidateUtil;
import com.asu.conceptcode.util.ConnectionFactory;
import com.asu.conceptcode.util.ConstantUtil;
import com.asu.conceptcode.util.RxnormResult;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

/**
 * @author ripudamansingh
 *
 */
public class RXNormCodeforTermRXNav {

	/**
	 * Function that retrieves the highest ranked rxnorm concepts from rxnav for
	 * an input string term
	 * 
	 * @param  String term from excel file
	 * @return Returns Set of candidates having maximum score (in the top 10)
	 *         for the term specified
	 */
	public HashSet<CandidateUtil> retrieveFromRXNormConceptsAPI(String term) {
		RestAssured.baseURI = ConstantUtil.BASE_RXNORM_URI;
		String path = "approximateTerm?term=" + term + "&maxEntries=10&option=1";
		Response response = given().request().get(path);
		String output = response.getBody().asString();
		XmlPath xmlPath = new XmlPath(output);
		List<CandidateUtil> candidates = xmlPath.getList("rxnormdata.approximateGroup.candidate", CandidateUtil.class);
		HashSet<CandidateUtil> candidatepropset = new HashSet<>(candidates);
		return candidatepropset;
	}

	/**
	 * Function that creates the rxnorm island for a search result candidate
	 * from rxnav. It also puts that candidate in the island. Returns Map
	 * containing the island for a rxnorm concept, with key-tty and
	 * value-candidateproperty object
	 * 
	 * @param  Candidate set retrieved from retrieveFromRXNormConceptsAPI()
	 * @return Returns Map containing the island for a rxnorm concept, with
	 *         key-tty and value-candidateproperty object
	 */
	public Map<String, List<CandidateRelatedUtil>> retrieveFromRXNormPropertiesAPI(Set<CandidateUtil> candidates) {
		RestAssured.baseURI = ConstantUtil.BASE_RXNORM_URI;
		Map<String, List<CandidateRelatedUtil>> candidatePropMap = new HashMap<>();
		for (CandidateUtil candidate : candidates) {
			CandidateRelatedUtil cd = new CandidateRelatedUtil();
			cd.setRxcui(candidate.getRxcui());
			// get umls cui of current candidate
			String path = "rxcui/" + candidate.getRxcui() + "/property?propName=UMLSCUI";
			Response response = given().request().get(path);
			String output = response.getBody().asString();
			XmlPath xmlPath = new XmlPath(output);
			cd.setUmlscui(xmlPath.getString("rxnormdata.propConceptGroup.propConcept.propValue"));

			// get current tty
			path = "rxcui/" + candidate.getRxcui() + "/property?propName=TTY";
			response = given().request().get(path);
			output = response.getBody().asString();
			xmlPath = new XmlPath(output);
			cd.setTty(xmlPath.getString("rxnormdata.propConceptGroup.propConcept.propValue"));

			// get current name
			path = "rxcui/" + candidate.getRxcui() + "/property?propName=RxNorm Name";
			response = given().request().get(path);
			output = response.getBody().asString();
			xmlPath = new XmlPath(output);
			cd.setName(xmlPath.getString("rxnormdata.propConceptGroup.propConcept.propValue"));

			// get other tty rxnorm atoms related to this rxnorm concept
			for (String tty : ConstantUtil.TTYLIST) {
				path = "rxcui/" + candidate.getRxcui() + "/related?tty=" + tty;
				response = given().request().get(path);
				output = response.getBody().asString();
				xmlPath = new XmlPath(output);
				List<CandidateRelatedUtil> candidateProps = new ArrayList<>(xmlPath
						.getList("rxnormdata.relatedGroup.conceptGroup.conceptProperties", CandidateRelatedUtil.class));
				if (!candidateProps.isEmpty()) {
					if (cd.getTty().equals(tty)) {
						boolean add = true;
						for (CandidateRelatedUtil can : candidateProps) {
							if (can.getRxcui().equals(cd.getRxcui()))
								add = false;
						}
						if (add)
							candidateProps.add(cd);
					}
					candidatePropMap.put(tty, candidateProps);
				}
			}
		}
		return candidatePropMap;
	}

	/**
	 * Function that writes to RxnormResult object passed sensitivity details :
	 * true/false, the code that was found in vs, its tty, term
	 * 
	 * @param  Rxnorm island map
	 * @param  ResultObject
	 * @return Returns void
	 */
	public void classifySensitivity(Map<String, List<CandidateRelatedUtil>> rxnormIslandMap, RxnormResult resultObj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		boolean notFound = true;
		try {
			con = ConnectionFactory.getConnection();
			for (Map.Entry<String, List<CandidateRelatedUtil>> entry : rxnormIslandMap.entrySet()) {
				for (CandidateRelatedUtil rxcuiIN : entry.getValue()) {
					ps = con.prepareStatement(
							"SELECT t2.category FROM valueset_rxnorm t1 INNER JOIN valueset_category t2 ON t1.category = t2.code WHERE t1.code = ?");
					ps.setString(1, rxcuiIN.getRxcui());
					rs = ps.executeQuery();
					if (rs.next()) {
						notFound = false;
						resultObj.setSensitive(true);
						resultObj.setSensitiveCode(rxcuiIN.getRxcui());
						resultObj.setSensitiveTerm(rxcuiIN.getName());
						resultObj.setSensitiveCategory(rs.getString(1));
						resultObj.setSensitiveTty(entry.getKey());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (notFound) {
			resultObj.setSensitive(false);
		}
	}

	/**
	 * Driver Function
	 * 
	 */
	public static void main(String[] args) {
		// list of med terms from excel
		List<String> termList = null;
		// set of rxnorm concepts received from search algo in rxnav
		Set<CandidateUtil> searchSet = null;
		// map of rxnorm island for a concept
		Map<String, List<CandidateRelatedUtil>> rxnormIslandMap = null;
		// result map from sensitivity check
		List<RxnormResult> resultList = new ArrayList<>();

		RXNormCodeforTermRXNav rxncs = new RXNormCodeforTermRXNav();

		// read from excel file
		termList = ReadFromExcel.getTermsWithSheetColumnName("Meds", "Medication/Dose", "term_med");
		// remove heading
		termList.remove(0);
		for (String term : termList) {
			RxnormResult resultObj = new RxnormResult();
			resultObj.setTerm(term);
			searchSet = rxncs.retrieveFromRXNormConceptsAPI(term);
			resultObj.setRxcuiSet(searchSet);
			if (!searchSet.isEmpty()) {
				rxnormIslandMap = rxncs.retrieveFromRXNormPropertiesAPI(searchSet);
				rxncs.classifySensitivity(rxnormIslandMap, resultObj);
			}
			resultList.add(resultObj);
		}
		// write to excel file
		try {
			WriteToExcel.writeToRxnormMed(resultList);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Input Workbook updated");
	}
}
