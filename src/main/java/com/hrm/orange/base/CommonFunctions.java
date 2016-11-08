package com.hrm.orange.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrm.orange.pages.LoginPage;

public class CommonFunctions {
	
	private static final By LogOutLink = By.id("welcome");

	private static final By LogOutBtn = By.xpath("//a[text()='Logout']");
	
	private WebDriver driver;
	
	public CommonFunctions(WebDriver driver)
	{
		this.driver =  driver;
		
	}
	
	public LoginPage LogOut()
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.findElement(LogOutLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
		driver.findElement(LogOutBtn).click();
		return new LoginPage(driver);
	}
	
	
}
