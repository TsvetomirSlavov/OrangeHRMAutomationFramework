package com.hrm.orange.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class LeavePage {
	
	private  WebDriver driver;
	WebDriverWait wait; 
	
	By ApplyLeaveTabTitle = By.xpath("//h1[text()='Apply Leave']");
	By leaveTypeDropdown = By.id("applyleave_txtLeaveType");
	By leaveFromDate = By.id("applyleave_txtFromDate");
	By leaveToDate = By.id("applyleave_txtToDate");
	By commentTextBox = By.id("applyleave_txtComment");
	By applyBtn = By.id("applyBtn");
	By leaveBalance = By.cssSelector("applyleave_leaveBalance");
	
	public LeavePage(WebDriver driver)
	{
		
		this.driver = driver;
	}
	//method to get text from Leave Page
	public String GetLeavePageText()
	{
		String ApplyLeavePageText ="";
		ApplyLeavePageText = driver.findElement(ApplyLeaveTabTitle).getText();
		return ApplyLeavePageText;
	}
	
	
	
	public void applyLeave(String leaveType, String fromDate, String toDate, String comment)
	{
		new WebDriverWait(driver,10);
		WebElement element = driver.findElement(leaveTypeDropdown);
		Select dropdown  = new Select(element);
		dropdown.selectByVisibleText(leaveType);
		element = driver.findElement(leaveFromDate);
		element.clear();
		element.sendKeys(fromDate);
		element.submit();
		element = driver.findElement(leaveToDate);
		element.clear();
		element.sendKeys(toDate);
		element.submit();
		driver.findElement(commentTextBox).sendKeys(comment);
		wait.until(ExpectedConditions.presenceOfElementLocated(leaveBalance));
	String text = 	driver.findElement(leaveBalance).getText();
	System.out.print(text);
		
		driver.findElement(applyBtn).click();		
		//JavascriptExecutor  jse = (JavascriptExecutor)driver;
		//System.out.println(jse.executeScript("return document.body.outerHTML"));
		//driver.findElement(By.className("message warning fadable")).getText();
		
	}
	
	
	

}
