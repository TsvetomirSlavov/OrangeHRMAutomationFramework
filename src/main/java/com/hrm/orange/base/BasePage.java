package com.hrm.orange.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.hrm.orange.helper.PropertiesFileReader;
import com.hrm.orange.pages.Dashboard;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/***
 * 
 * @author clogeny
 *
 */
public class BasePage
{
	protected static WebDriver driver;
	static String driverPath = "E:\\drivers\\";
	public static ExtentReports extent = new ExtentReports(setReportingPath(), true);
	public static ExtentTest extentLogger;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	//private static String browserType = prreader.getPropertyvalues("BROWSER_TYPE");
	private static String appURL = prreader.getPropertyvalues("APPLICATION_URL");

	static By UsernameField = By.id("txtUsername");
	static By PasswordField = By.id("txtPassword");
	static By LoginBtn = By.id("btnLogin");
	static By LoginFailedMsg = By.id("spanMessage");

	// System.out.println("Im here");
	public static String setReportingPath()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss");
		String path = "E:\\TestResults\\Reports\\Report" + sdf.format(date) + "\\TestReport" + sdf1.format(date) + ".html";
		return path;
	}

	public static void setDriver(String browserType)
	{
		switch (browserType)
		{
		case "chrome":
			driver = initChromeDriver();
			break;
		case "firefox":
			driver = initFirefoxDriver();
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver();
		}
	}

	private static WebDriver initChromeDriver()
	{
		System.out.println("Launching google chrome with new profile..");
		;
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver()
	{
		System.out.println("Launching Firefox browser..");
		// System.setProperty("webdriver.gecko.driver", driverPath
		// + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public static Dashboard LoginToHRM(String UserName, String Password)
	{

		driver.findElement(UsernameField).sendKeys(UserName);
		extentLogger.log(LogStatus.INFO, "UserName entered");
		driver.findElement(PasswordField).sendKeys(Password);
		extentLogger.log(LogStatus.INFO, "password entered");
		driver.findElement(LoginBtn).click();
		extentLogger.log(LogStatus.INFO, "Login button clicked");

		return new Dashboard(driver);
	}

	public static String GetLoginFailureMessage()
	{
		String LoginErrorMsg = "";
		try
		{

			LoginErrorMsg = driver.findElement(LoginFailedMsg).getText();

		} catch (Exception e)
		{
			System.out.print("Could not find the element" + e.getMessage());
		}
		return LoginErrorMsg;
	}

	public static void closeBrowser()
	{
		driver.quit();
	}

	public static WebDriver getDriver()
	{
		// TODO Auto-generated method stub
		return driver;
	}
}
