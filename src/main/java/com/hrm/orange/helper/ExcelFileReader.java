package com.hrm.orange.helper;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelFileReader
{
	public String  readexcel(String file, int i, int j) throws BiffException, IOException{
		
	Workbook book = Workbook.getWorkbook(new File(file));
	Sheet sheet = book.getSheet("Sheet1");
	  String value = sheet.getCell(j,i).getContents();
	  book.close();
	    return value;
	    
        
    }

}
