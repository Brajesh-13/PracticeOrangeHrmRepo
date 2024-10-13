package orangehrm.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import orangehr.utilities.Utility;

public class Base {
	
	WebDriver driver;
	public Properties prop ;
	public Properties dataprop;
	
	
	// this method to load the properties file
	public void loadpropertiesfile() 
	{
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\orngehrm\\config\\config.properties");
		try {
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
		}catch(Throwable e)
		{
			
			e.printStackTrace();
			
		}
		
		dataprop = new Properties();
		File datapropfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\orangehrm\\testdata\\Testdata.properties");
		try {
		FileInputStream fisdata = new FileInputStream(datapropfile);
		dataprop.load(fisdata);
		}catch(Throwable e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public WebDriver initializebrowsserandopenurl(String browserName) 
	{
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			driver= new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) 
		{
			driver = new EdgeDriver();
		}
		
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.Implicit_Wait_Time));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.Page_Load_Time));
	driver.get(prop.getProperty("url"));
	return driver;
	}
}
