package com.hrm.orange.pages;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class ApproveLeave
{
	private  WebDriver driver;
	
	By pendingLeaveTable = By.xpath("//*[@id='resultTable']/tbody/tr");

	public ApproveLeave(WebDriver driver)
	{
		this.driver = driver;
	}

public void approveLeave(String date){
		
		List<WebElement> tRows = driver.findElements(pendingLeaveTable);
		int totalRows = tRows.size();
		int rowNum=1,colNum;
		for(WebElement trElement : tRows)
		{
			colNum=1;
			WebElement tCols = trElement.findElement(By.xpath("td[1]"));
			System.out.println(tCols.getText());
			if((tCols.getText()).equals(date))
			{
				WebElement e = trElement.findElement(By.className("select_action quotaSelect"));
				Select select= new Select(e);
				select.selectByVisibleText("Approve");
				driver.findElement(By.id("btnSave")).click();
				break;
				
			}
			
			
				rowNum++;		
		}		
		
	}
}
