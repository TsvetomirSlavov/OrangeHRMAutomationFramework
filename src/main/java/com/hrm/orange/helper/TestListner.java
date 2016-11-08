package com.hrm.orange.helper;

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

import com.hrm.orange.test.AppTest;

public class TestListner implements ITestListener 
{

	public void onTestStart(ITestResult result)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void onTestSuccess(ITestResult result)
	{

		//Added my comment here..
	}

	public void onTestFailure(ITestResult result)
	{
		try{
		Date date = new Date(); //creates a date based on current date/time

		//provides a formatting string for your eventual output
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss");
		Object currentClass = result.getInstance();
        WebDriver webDriver = ((AppTest)currentClass).getDriver();
		
	File src = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("E:\\Report\\Report"+sdf.format(date)+"\\"+result.getMethod().getMethodName()+sdf1.format(date)+".jpg"));
		}
		
		catch(Exception e)
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
