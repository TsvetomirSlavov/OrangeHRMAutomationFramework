package com.hrm.orange.pages;

import org.openqa.selenium.*;
import org.testng.Reporter;





public class LoginPage {

	protected  WebDriver driver;
	//ExtentTest logger;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		
				
	}
	
	
	By UsernameField = By.id("txtUsername");
	By PasswordField = By.id("txtPassword");
	By LoginBtn	= By.id("btnLogin");
	By LoginFailedMsg = By.id("spanMessage");
	
	//String URL = "http://127.0.0.1/orangehrm-3.3.1/symfony/web/index.php/auth/login";

	
	public Dashboard LoginToHRM(String UserName, String Password)
	{
		//logger = extent.startTest("Test11");
		//com.hrm.orange.tests.ORANGE_HRM_TESTS.logger.log(LogStatus.INFO, "In Login function now ");
		//driver.get(URL);
		driver.findElement(UsernameField).sendKeys(UserName);
		Reporter.log("Entered username");
		driver.findElement(PasswordField).sendKeys(Password);
		Reporter.log("Entered password");
		driver.findElement(LoginBtn).click();
		Reporter.log("clicked on login button");
					
		return new Dashboard(driver);
	}
	
	public String GetLoginFailureMessage()
	{
		String LoginErrorMsg="";
		try{
		
		LoginErrorMsg = driver.findElement(LoginFailedMsg).getText();
		
		}
		catch(Exception e)
		{
			System.out.print("Could not find the element" +e.getMessage());
		}
		return LoginErrorMsg;
	}
	
	
	
	
	
}
