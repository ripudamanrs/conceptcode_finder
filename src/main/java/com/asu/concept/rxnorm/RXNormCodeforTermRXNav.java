package com.asu.concept.rxnorm;

import static com.jayway.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.asu.conceptcode.excel.ReadFromExcelUtil;
import com.asu.conceptcode.util.CandidateRelatedUtil;
import com.asu.conceptcode.util.CandidateUtil;
import com.asu.conceptcode.util.ConnectionFactory;
import com.asu.conceptcode.util.ConstantUtil;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

/**
 * @author ripudamansingh
 *
 */
public class RXNormCodeforTermRXNav {
	
	// list of island properties to look up for a concept
	List<String> ttyList = new ArrayList<>(
			Arrays.asList("IN", "PIN", "MIN", "BN", "SCD", "BPCK", "SBD", "GPCK", "BPCK", "SCDG", "DFG", "SBDG"));

	/**
	 * Function that retrieves 
	 * 
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
	 * Function that retrieves 
	 * 
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
			for (String tty : ttyList) {
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
	 * Function that retrieves 
	 * 
	 */
	public void checkSensitivity(Map<String, List<CandidateRelatedUtil>> candidateMap) {	
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		boolean notFound = true;
		try {
			con = ConnectionFactory.getConnection();
			System.out.println("Checking for Sensitivity");
			System.out.println("------------------------");
			for (Map.Entry<String, List<CandidateRelatedUtil>> entry : candidateMap.entrySet()) {
				System.out.println("Checking for TTY: " + entry.getKey());
				for (CandidateRelatedUtil rxcuiIN : entry.getValue()) {
					System.out.println("Checking for : " + entry.getKey() + " RXCUI: " + rxcuiIN.getRxcui());
					ps = con.prepareStatement("select count(*) from rxnorm where code = ?");
					ps.setString(1, rxcuiIN.getRxcui());
					rs = ps.executeQuery();
					if (rs.next() && rs.getInt(1) != 0) {
						System.out.println(
								"-----------------------------------------------------------------------------");
						System.out.println("Sensitivity Found for RXCUI :" + rxcuiIN.getRxcui() + " with Name :"
								+ rxcuiIN.getName() + "with TTY :" + entry.getKey());
						notFound = false;
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
			System.out.println("Med not Sensitive Information!");
		}
	}

	/**
	 * Driver Function
	 * 
	 */
	public static void main(String[] args) {
		
		RXNormCodeforTermRXNav rxncs = new RXNormCodeforTermRXNav();
		
		// read from excel file (using Wentao's impl for reading from excel)
		List<String> terms = ReadFromExcelUtil.getTermsWithSheetColumnName("Meds","Medication/Dose","term_med");
		
		// remove heading
		terms.remove(0);
		
		Map<String, List<CandidateRelatedUtil>> candidateMap = null;
		
		Set<CandidateUtil> candidates = new HashSet<>(rxncs.retrieveFromRXNormConceptsAPI(terms.get(0)));
		
		System.out.println("List obtained by string match from api: ");
		System.out.println(candidates);
		System.out.println("");
		
		if(!candidates.isEmpty()) {
			candidateMap = rxncs.retrieveFromRXNormPropertiesAPI(candidates);
			System.out.println("RxNorm Tree");
			System.out.println("-----------");
			for (Map.Entry<String, List<CandidateRelatedUtil>> entry : candidateMap.entrySet()) {
				System.out.println("TTY: " + entry.getKey());
				for (CandidateRelatedUtil rxcuiIN : entry.getValue()) {
					System.out.println("Concept: " + rxcuiIN);
				}
			}
			System.out.println("");
			rxncs.checkSensitivity(candidateMap);
		} 
	}
}
