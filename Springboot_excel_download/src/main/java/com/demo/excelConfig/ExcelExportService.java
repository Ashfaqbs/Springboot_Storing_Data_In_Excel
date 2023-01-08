package com.demo.excelConfig;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.model.Car;

public class ExcelExportService {

	
	
	//exporting excel service
	 public XSSFWorkbook generateExcelFile(List<Car> cars) {
	      XSSFWorkbook workbook = new XSSFWorkbook();
	      Sheet sheet = workbook.createSheet("Sheet1");

	      Row headerRow = sheet.createRow(0);
	      headerRow.createCell(0).setCellValue("ID");
	      headerRow.createCell(1).setCellValue("Name");
	      headerRow.createCell(2).setCellValue("Price");

	      int rowNum = 1;
	      for (Car car : cars) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(car.getcId());
	         row.createCell(1).setCellValue(car.getcName());
	         row.createCell(2).setCellValue(car.getCost());
	      }

	      return workbook;
	   }

	
}
