package com.hrm.orange.test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.*;
import com.hrm.orange.base.BasePage;
import com.hrm.orange.helper.ExcelFileReader;
import com.hrm.orange.helper.PropertiesFileReader;
import com.hrm.orange.pages.ApproveLeave;
import com.hrm.orange.pages.Dashboard;
import com.hrm.orange.pages.LeavePage;
import com.relevantcodes.extentreports.LogStatus;

import jxl.read.biff.BiffException;

public class AppTest extends BasePage
{
	// private WebDriver driver;
	// BasePage bpage = new BasePage();
	static PropertiesFileReader prreader = new PropertiesFileReader();
	private static String browserType = prreader.getPropertyvalues("BROWSER_TYPE");
	// private static String appURL =
	// prreader.getPropertyvalues("APPLICATION_URL");

	@BeforeMethod
	public void setup()
	{
		BasePage.setDriver(browserType);
	}

	@AfterMethod
	public void tearDown()
	{
		BasePage.closeBrowser();
		extent.endTest(extentLogger);

		// writing everything to document.
		extent.flush();
	}

	@Test(enabled = true)
	public void VerifyLoginSuccess() throws Exception
	{

		//CommonFunctions cfunction = new CommonFunctions();
		BasePage.extentLogger = extent.startTest("VerifyLoginSuccess", "Verify login with valid Credentials");
		extentLogger.log(LogStatus.INFO, "test1");
		ExcelFileReader excel = new ExcelFileReader();
		String file = ".\\src\\test\\resources\\testdata\\TestAuto.xls";
		String userName = excel.readexcel(file, 1, 0);
		String password = excel.readexcel(file, 1, 1);
		Dashboard dashboard = BasePage.LoginToHRM(userName, password);
		String ActualText = dashboard.GetDashboardText();
		Assert.assertEquals(ActualText, "Dashboard");
		if (ActualText.equals("Dashboard"))
		{
			extentLogger.log(LogStatus.PASS, "Test Case Passed");
		} else
		{
			extentLogger.log(LogStatus.FAIL, "Test Case Failed");
		}

	}

	@Test(enabled = true)
	public void VerifyLoginFailure() throws Exception
	{
		BasePage.extentLogger = extent.startTest("VerifyLoginFailure", "Verify login with invalid Credentials");
		extentLogger.log(LogStatus.INFO, "test 2");
		ExcelFileReader excel = new ExcelFileReader();
		String file = ".\\src\\test\\resources\\testdata\\TestAuto.xls";
		String userName = excel.readexcel(file, 2, 0);
		String password = excel.readexcel(file, 2, 1);
		BasePage.LoginToHRM(userName, password);
		String ActualText = BasePage.GetLoginFailureMessage();
		Assert.assertEquals(ActualText, "Invalid credentials");
		if (ActualText == "Invalid credentials")
		{
			extentLogger.log(LogStatus.PASS, "Test Case Passed");
		} else
		{
			extentLogger.log(LogStatus.FAIL, "Test Case Failed");
		}

	}

	@DataProvider(name = "test1")
	public static Object[][] leaveDetails()
	{

		return new Object[][]
		{
				{ "Casual", "2016-11-25", "2016-11-25", "test comment" } };

	}

	@Test(dataProvider = "test1", enabled = false)
	public void VerifyApplyLeave(String leaveType, String fromDate, String toDate, String comment) throws Exception
	{

		//CommonFunctions cfunction = new CommonFunctions();

		ExcelFileReader excel = new ExcelFileReader();
		String file = ".\\src\\test\\resources\\testdata\\TestAuto.xls";
		String userName = excel.readexcel(file, 1, 0);
		String password = excel.readexcel(file, 1, 1);
		Dashboard dashboard = BasePage.LoginToHRM(userName, password);
		// String ActualText = dashboard.GetDashboardText();
		// BasePage.LoginToHRM(userName, password);
		LeavePage leavepage = dashboard.goToLeavePage();
		leavepage.applyLeave(leaveType, fromDate, toDate, comment);

	}

	@Test(enabled = false)
	public void verifymy() throws BiffException, IOException
	{

		//CommonFunctions cfunction = new CommonFunctions();

		ExcelFileReader excel = new ExcelFileReader();
		String file = ".\\src\\test\\resources\\testdata\\TestAuto.xls";
		String userName = excel.readexcel(file, 2, 0);
		String password = excel.readexcel(file, 2, 1);
		Dashboard dashboard = BasePage.LoginToHRM(userName, password);
		// String ActualText = dashboard.GetDashboardText();
		ApproveLeave approveleave = dashboard.goToApproveLeavePage();
		approveleave.approveLeave("2016-11-25");

	}

}
