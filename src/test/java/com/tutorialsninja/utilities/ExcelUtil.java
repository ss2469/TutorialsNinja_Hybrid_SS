package com.tutorialsninja.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtil {

	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	@DataProvider(name = "TNRegister")
	public Object[][] getTNExcelRegisterData() throws Exception {
		Object[][] data = ExcelUtil.readFromExcelSheetTNRegister("RegisterTN");
		return data;
	}

	public static Object[][] readFromExcelSheetTNRegister(String sheetName) throws Exception {

		ip = new FileInputStream("src/test/resources/ExcelDataTutorialsNinja.xlsx");

		workbook = new XSSFWorkbook(ip);

		sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		//int rows = 3;
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				// if(cell == null || cell.getCellType() == CellType.BLANK)
				// continue;
				CellType celltype = cell.getCellType();

				switch (celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}

		return data;
	}

}
