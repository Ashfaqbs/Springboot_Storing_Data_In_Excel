


         <dependency>
   <groupId>org.apache.poi</groupId>
  <artifactId>poi</artifactId>
  <version>5.2.0</version>
</dependency>

            <dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>5.2.0</version>
</dependency>



 package com.demo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelComparator {

	@GetMapping("/testingXL")
	public void main() {
        String excelFilePath1 = "test1.xlsx";
        String excelFilePath2 = "test2.xlsx";

        Set<String> column1Data1 = extractFirstColumnData(excelFilePath1);
        Set<String> column1Data2 = extractFirstColumnData(excelFilePath2);

        // Find common values
        Set<String> commonValues = new HashSet<>(column1Data1);
        commonValues.retainAll(column1Data2);

        System.out.println("Common values in the first columns:");
        for (String value : commonValues) {
            System.out.println(value);
        }
    }

//	/Basic_AWS/src/test1.xlsx
    private static Set<String> extractFirstColumnData(String excelFilePath) {
        Set<String> columnData = new HashSet<>();

//        try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
        
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet is used

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // Assuming the first column is used
                if (cell != null) {
                	 if (cell.getCellType() == CellType.NUMERIC) {
                         // Handle numeric values
                		 String cellValue = String.valueOf(cell.getNumericCellValue());
                		 columnData.add(cellValue);
                	 }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnData;
    }
	
	
	
}


