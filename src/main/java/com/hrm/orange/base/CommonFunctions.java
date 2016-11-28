package com.hrm.orange.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonFunctions extends BasePage {
	
	private static final By LogOutLink = By.id("welcome");

	private static final By LogOutBtn = By.xpath("//a[text()='Logout']");
	
	private WebDriver driver;
	
	public CommonFunctions()
	{
		super();
		
	}
	
	public BasePage LogOut()
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.findElement(LogOutLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
		driver.findElement(LogOutBtn).click();
		return new BasePage();
	}
	
	
}
