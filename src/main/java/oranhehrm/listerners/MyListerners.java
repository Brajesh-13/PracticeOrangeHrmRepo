package oranhehrm.listerners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import orangehr.utilities.ExtentReporter;
import orangehr.utilities.Utility;

public class MyListerners implements ITestListener{
	ExtentReports extentreport;
	ExtentTest extentTest ;
	String testname;
	@Override
	public void onStart(ITestContext context) {
	   extentreport = ExtentReporter.GenerateExtentReport();
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		//String testname = result.getName();
		//Making global testname 
		
		testname = result.getName();
		extentTest = extentreport.createTest(testname);
		extentTest.log(Status.INFO,testname+ " Startted Executing");
		//System.out.println("Execution of Project Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//Global Declare so commenting out here
		//as we have testname global with is coming from OnTestStart so no need to declare separately
		//String testname = result.getName();
		extentTest.log(Status.PASS,testname+ " Got Successfully Executed");
		
		//System.out.println(testname+ "Got Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//String testname = result.getName();
		WebDriver driver = null;
		
		//System.out.println(testname+"Got Failed");
		try {
	    driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		//code for Calling Screenshot method
		String  DestinationScreenshotpath =Utility.CaptureScreenShot(driver, testname);
		
		
		// code to attach Screenshot to extentreport
		
		extentTest.addScreenCaptureFromPath(DestinationScreenshotpath);
		
		// this line for showing exception details in report
		extentTest.log(Status.INFO, result.getThrowable());
		
		extentTest.log(Status.FAIL, testname+" Got Failed");
		
		
		
		
		//System.out.println(result.getThrowable());
		//System.out.println(testname+" Got Failed");
		//System.out.println("Screenshot taken");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//String testname = result.getName();
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname+" Got Skipped");
		//System.out.println(testname+"Got Skipped");
		//System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		//System.out.println("Project Execution got Finished");
		
		
		// Code to Open ExtentReport Automatically Instead opening Manually
		
		String Pathofextentreport= System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File ExtentReportFile = new File(Pathofextentreport);
		
		try {
			Desktop.getDesktop().browse(ExtentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
