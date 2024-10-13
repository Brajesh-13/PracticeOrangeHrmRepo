package orangehr.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports GenerateExtentReport() 
	{
		
	  ExtentReports extentreport = new ExtentReports();	
	  // here provide the path where the reports will be created
	  File extentreportfile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	  ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentreportfile);
	  
	  sparkreporter.config().setTheme(Theme.DARK);
	  sparkreporter.config().setReportName("Orange HRM Test Automation Results");
	  sparkreporter.config().setDocumentTitle("Orange HRM Automation Report");
	  sparkreporter.config().setTimeStampFormat("dd/MM/YYYY hh::mm:ss");
	  //Here Attaching spark reporter to extent reporter
	  
	  extentreport.attachReporter(sparkreporter);
	  
	  //To fetch data from config file & use in Extent report 
	  
	  Properties configprop = new Properties();
	  File configpropfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\orngrhrm\\config\\config.properties");
	  try {
	  FileInputStream fisconfigprop = new FileInputStream(configpropfile);
	  configprop.load(fisconfigprop);
	  }catch(Throwable e){
		  e.printStackTrace();
	  }
	  // To Set some system information in extent report 
	  extentreport.setSystemInfo("Application URL",configprop.getProperty("url"));
	  extentreport.setSystemInfo("Browser Name", configprop.getProperty("browserName"));
	  extentreport.setSystemInfo("Operating System", System.getProperty("OS.name"));
	  extentreport.setSystemInfo("UserName", System.getProperty("user.name"));
	  extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
	  
	  return extentreport;
	  
	}

}
