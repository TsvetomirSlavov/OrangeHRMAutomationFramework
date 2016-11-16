package com.hrm.orange.help;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.hrm.orange.test.OrangeBaseTest;

public class TestListner implements ITestListener
{
	String screnShotDirectory = "E:\\Report\\screenshots";

	public void onTestStart(ITestResult result)
	{
		Reporter.log("On Start?");
		

	}

	public void onTestSuccess(ITestResult result)
	{

		Reporter.log("Test case passed successfully");
	}

	public  void onTestFailure(ITestResult result)
	{
		try
		{
			Date date = new Date(); // creates a date based on current date/time

			// provides a formatting string for your eventual output
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss");
			Object currentClass = result.getInstance();
			WebDriver driver =  ((OrangeBaseTest) currentClass).getDriver();
			
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File f = new File(screnShotDirectory + sdf.format(date) + "\\" + result.getMethod().getMethodName()
					+ sdf1.format(date) + ".jpg");
			FileUtils.copyFile(src, f);
			Reporter.log("<a href=" + "file://" + f.getAbsolutePath() + ">Detailed Report</a>");
			Reporter.log("Test Case Failed");

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result)
	{
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context)
	{
		// TODO Auto-generated method stub
		

	}

	public void onFinish(ITestContext context)
	{
		// TODO Auto-generated method stub

	}

}
