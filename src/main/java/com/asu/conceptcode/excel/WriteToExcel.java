package com.asu.conceptcode.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.asu.conceptcode.util.CandidateUtil;
import com.asu.conceptcode.util.ConstantUtil;
import com.asu.conceptcode.util.RxnormResult;

public class WriteToExcel {

	public static void writeToRxnormMed(List<RxnormResult> resultList)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		File file = new File(ConstantUtil.FILE_NAME);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet("Meds");
		Integer columnNum = 0;
		int i = 0;
		// add rxCUI column
		Row row = sheet.getRow(i);
		if (row != null) {
			columnNum = getNextAvailableColumn(row);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue((String) "Codes");
			i = 1;
		}
		for (RxnormResult result : resultList) {
			row = sheet.getRow(i);
			Cell cell = row.createCell(columnNum);
			StringBuilder codes = new StringBuilder(" ");
			for (CandidateUtil candidate : result.getRxcuiSet()) {
				codes.append(candidate.getRxcui());
				codes.append("; ");
			}
			cell.setCellValue((String) codes.toString());
			i++;
		}

		// add isSensitive? column
		i = 0;
		row = sheet.getRow(i);
		if (row != null) {
			columnNum = getNextAvailableColumn(row);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue((String) "Is Sensitive?");
			i = 1;
		}
		for (RxnormResult result : resultList) {
			row = sheet.getRow(i);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue((boolean) result.isSensitive());
			i++;
		}

		// add sensitiveTerm column
		i = 0;
		row = sheet.getRow(i);
		if (row != null) {
			columnNum = getNextAvailableColumn(row);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue((String) "Sensitive Term");
			i = 1;
		}
		for (RxnormResult result : resultList) {
			row = sheet.getRow(i);
			Cell cell = row.createCell(columnNum);
			if (result.getSensitiveTerm() != null) {
				cell.setCellValue((String) result.getSensitiveTerm());
			} else {
				cell.setCellValue((String) "N/A");
			}
			i++;
		}

		// add sensitiveCode column
		i = 0;
		row = sheet.getRow(i);
		if (row != null) {
			columnNum = getNextAvailableColumn(row);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue((String) "Sensitive Code");
			i = 1;
		}
		for (RxnormResult result : resultList) {
			row = sheet.getRow(i);
			Cell cell = row.createCell(columnNum);
			if (result.getSensitiveCode() != null) {
				cell.setCellValue((String) result.getSensitiveCode());
			} else {
				cell.setCellValue((String) "N/A");
			}
			i++;
		}
		
		// add sensitiveCategory column
				i = 0;
				row = sheet.getRow(i);
				if (row != null) {
					columnNum = getNextAvailableColumn(row);
					Cell cell = row.createCell(columnNum);
					cell.setCellValue((String) "Sensitive Category");
					i = 1;
				}
				for (RxnormResult result : resultList) {
					row = sheet.getRow(i);
					Cell cell = row.createCell(columnNum);
					if (result.getSensitiveCategory() != null) {
						cell.setCellValue((String) result.getSensitiveCategory());
					} else {
						cell.setCellValue((String) "N/A");
					}
					i++;
				}

		// add sensitiveTTY column
		i = 0;
		row = sheet.getRow(i);
		if (row != null) {
			columnNum = getNextAvailableColumn(row);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue((String) "Sensitive TTY");
			i = 1;
		}
		for (RxnormResult result : resultList) {
			row = sheet.getRow(i);
			Cell cell = row.createCell(columnNum);
			if (result.getSensitiveTty() != null) {
				cell.setCellValue((String) result.getSensitiveTty());
			} else {
				cell.setCellValue((String) "N/A");
			}
			i++;
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	public static Integer getNextAvailableColumn(Row row) {
		int j = 0;
		while (row.getCell(j) != null) {
			j++;
		}
		return j;
	}
}
