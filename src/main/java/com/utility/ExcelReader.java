package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.base.BaseClass;

public class ExcelReader extends BaseClass {
	
	public static FileInputStream excelSheetRead(String excelName) throws FileNotFoundException {
		System.out.println(projectpath+"/src/test/resources/data/"+excelName);
		
		
		FileInputStream file = new FileInputStream(projectpath+"/src/test/resources/data/userData/"+excelName);
		return file;
	}
	
	public static Sheet getSheet(FileInputStream file,String sheetName) throws EncryptedDocumentException, IOException {
		
	Sheet sh = WorkbookFactory.create(file).getSheet(sheetName);
	
	return sh;
	}
	public static Object getSingleCellData(Sheet sh,int rowNum,int cellNum) {
		
		if(sh.getRow(rowNum).getCell(cellNum).getCellType().toString().equalsIgnoreCase("string"))
			
		return	sh.getRow(rowNum).getCell(cellNum).getStringCellValue();
		else
		return	sh.getRow(rowNum).getCell(cellNum).getNumericCellValue();
	}
	
	public static Map getRowData(Sheet sh, int rowNum) {
		Map<String,Object> rowData = new HashMap<>();
		int cellNum = sh.getRow(rowNum).getLastCellNum();
		
		for(int i=0; i<cellNum; i++) {
			if(sh.getRow(rowNum).getCell(i).getCellType().toString().equalsIgnoreCase("string"))
		
				rowData.put(sh.getRow(0).getCell(i).getStringCellValue(),
						sh.getRow(rowNum).getCell(i).getStringCellValue());
			else
				
			rowData.put(sh.getRow(0).getCell(i).getStringCellValue(),
			sh.getRow(rowNum).getCell(i).getNumericCellValue());
		}
		return rowData;
		
		
	}
	public static void getAllExcelData(Sheet sh) {
		int rowNum = sh.getLastRowNum();
		for(int row=0; row<rowNum; row++ ) {
			Map<String,Object> allData = new HashMap<>();
			int cellNum = sh.getRow(row).getLastCellNum();
			
			for(int cell=0; cell<cellNum; cell++) {
				if(sh.getRow(row).getCell(cell).getCellType().toString().equalsIgnoreCase("string"))
					
				allData.put(sh.getRow(0).getCell(cell).getStringCellValue(),
						sh.getRow(row).getCell(cell).getStringCellValue());
				else
					
					allData.put(sh.getRow(0).getCell(cell).getStringCellValue(),
							sh.getRow(row).getCell(cell).getNumericCellValue());
				
			}
		}
	}

}
