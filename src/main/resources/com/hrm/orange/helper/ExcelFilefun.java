package com.hrm.orange.helper;

import java.io.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class ExcelFilefun {
	public String  readexcel(String file, int i, int j) throws BiffException, IOException{
	
	Workbook book = Workbook.getWorkbook(new File(file));
	Sheet sheet = book.getSheet("Sheet1");
	  String value = sheet.getCell(j,i).getContents();
	  book.close();
	    return value;
	    
        
    }
	
}



