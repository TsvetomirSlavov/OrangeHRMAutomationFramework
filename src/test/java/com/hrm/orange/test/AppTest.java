package com.hrm.orange.test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.hrm.orange.base.CommonFunctions;
import com.hrm.orange.helper.ExcelFileReader;
import com.hrm.orange.pages.ApproveLeave;
import com.hrm.orange.pages.Dashboard;
import com.hrm.orange.pages.LeavePage;
import com.hrm.orange.pages.LoginPage;
import org.openqa.selenium.*;

public class AppTest extends OrangeBaseTest
{
	private WebDriver driver;
	@BeforeMethod
	public void setup(){
		driver = getDriver();
	}
	

	@Test
	public void VerifyLoginSuccess() throws Exception
	{

		
		//driver = new FirefoxDriver();
		CommonFunctions cfunction = new CommonFunctions(driver);
		LoginPage login = new LoginPage(driver);
		Dashboard dashboard = new Dashboard(driver);
		ExcelFileReader excel = new ExcelFileReader();
		String file = ".\\src\\test\\resources\\testdata\\TestAuto.xls";
		String userName = excel.readexcel(file, 1, 0);
		String password = excel.readexcel(file, 1, 1);
		dashboard = login.LoginToHRM(userName, password);
		String ActualText = dashboard.GetDashboardText();
		Assert.assertEquals(ActualText, "Dashboard");
		cfunction.LogOut();
		//driver.close();
		//driver.quit();
		
		
	}



	@Test(enabled=true)
	public void VerifyLoginFailure() throws Exception
	{
			
			//driver = new FirefoxDriver();
			LoginPage login = new LoginPage(driver);
			ExcelFileReader excel = new ExcelFileReader();
			String file = ".\\src\\test\\resources\\testdata\\TestAuto.xls";
			String userName = excel.readexcel(file, 2, 0);
			String password = excel.readexcel(file, 2, 1);
			login.LoginToHRM(userName, password);
			String ActualText = login.GetLoginFailureMessage();
			Assert.assertEquals(ActualText, "Invalid credentials");
			//driver.close();
			//driver.quit();


	}

	@DataProvider(name = "test1")
	public static Object[][] leaveDetails()
	{

		return new Object[][]
		{
				{ "Casual", "2016-10-28", "2016-10-28", "test comment" } };

	}

	@Test(dataProvider = "test1", enabled = false)
	public void VerifyApplyLeave(String leaveType, String fromDate, String toDate, String comment) throws Exception
	{
		//driver = new FirefoxDriver();
		LoginPage login = new LoginPage(driver);
		Dashboard dashboard = new Dashboard(driver);
		LeavePage leavepage = new LeavePage(driver);
		login.LoginToHRM("rpande", "rpande");
		dashboard.goToLeavePage();
		leavepage.applyLeave(leaveType, fromDate, toDate, comment);
		driver.close();
		driver.quit();
	}

	@Test(enabled = false)
	public void verifymy()
	{
		//driver = new FirefoxDriver();
		LoginPage login = new LoginPage(driver);
		Dashboard dashboard = new Dashboard(driver);
		// LeavePage leavepage = new LeavePage(driver);
		ApproveLeave approveleave = new ApproveLeave(driver);
		login.LoginToHRM("cpande", "cpande");
		dashboard.goToApproveLeavePage();
		approveleave.approveLeave("2016-10-28");
		driver.close();
		driver.quit();

	}


}
