__# Springboot_Storing_Data_In_Excel
This is a springboot file to demonstrate  using of excel/csv file , here we are taking list of objects and passing it to a service which has a method when called it will store the data in excel file.

dependency  used in pom.xml

<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId> poi-ooxml</artifactId>
			<version>5.1.0</version>
		</dependency>

--service class
package com.demo.excelConfig;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Sheet;
import com.demo.model.Car;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class CarsExcelExporter {

	
	
	//method to take list of car data and write in excel and store it in target folder
	 public void writeDataToExcel(List<Car> cars) throws IOException {
		 //creating the excelworkbook
	      XSSFWorkbook workbook = new XSSFWorkbook();
	     //creating the sheet in that workbook
	      Sheet sheet = workbook.createSheet("Sheet1");

	      
	  //    creating the coulmns by targeting the 0th row
	      Row headerRow = sheet.createRow(0);
	      headerRow.createCell(0).setCellValue("ID");//providing the 0th , 1st and 2nd cell of the 0th row column names
	      headerRow.createCell(1).setCellValue("Name");
	      headerRow.createCell(2).setCellValue("Price");

	      
	    //  since we have data , we will create a loop and add data and increment the row , and so we will start the row from 1st as for 0th have given the column name
	      int rowNum = 1;
	      for (Car car : cars) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(car.getcId());
	         row.createCell(1).setCellValue(car.getcName());
	         row.createCell(2).setCellValue(car.getCost());
	      }

	      FileOutputStream outputStream = new FileOutputStream("target/car_data.xlsx");
	      workbook.write(outputStream);
	      outputStream.close();
	   }
}

