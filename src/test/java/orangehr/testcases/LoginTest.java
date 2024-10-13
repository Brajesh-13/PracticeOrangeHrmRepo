package orangehr.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import orangehr.utilities.Utility;
import orangehrm.baseclass.Base;
import orangehrm.pages.DashboardPage;
import orangehrm.pages.LoginPage;

public class LoginTest extends Base{
	public WebDriver driver;
	

	//Before each test method this beforeMethod will get executed
	@BeforeMethod
	public void Setup() 
	{
		loadpropertiesfile();
	driver = initializebrowsserandopenurl(prop.getProperty("browserName"));
	
	}
	
	
	
	//After each test method this AfterMethod will get executed
	@AfterMethod
	public void tearDown() {
	//if we don't use after method separately then if any test method will fail browser will not close automatically
	driver.quit();
	}
	
	@Test(priority=1,dataProvider="DifferentCredentialSupplier")
	//Using data driven method for this method, supply data from excel
	//This Method will run 3 times as we are providing 3 set of data 
	public void verifyloginwithvalidcredentials(String UserName,String Password) 
	{
	LoginPage loginpage = new LoginPage(driver);
	loginpage.EnterUserName(UserName);
	loginpage.EnterPassword(Password);
	loginpage.ClickOnLoginButton();
	
	DashboardPage dashboardpage = new DashboardPage(driver);
	Assert.assertTrue(dashboardpage.VerifyDasboardPageAfterLogin(),"Dasboard is not displayed");
	//Assert.assertTrue(driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).isDisplayed(),"Dasboard is not displayed");
	}
	
	
	// To supply data from excel file
	@DataProvider(name="DifferentCredentialSupplier")
     public Object[][] SupplyData() 
	{
		//here providing 3 set of data directly 
		/*Object [][] data = {{"Admin","admin123"},{"Admin","admin123"},{"Admin","admin123"}};
		
		 here providing set of data from excel file 
		
		return data;*/
		
		// here providing data from excel file 
		
		Object [][] data = Utility.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority=2)
	public void verifyloginwithinvalidusername() {
	//driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Utility.generatetimestampusername());
	//for above line using POM & so on
	LoginPage loginpage = new LoginPage(driver);
	loginpage.EnterUserName(Utility.generatetimestampusername());

	//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataprop.getProperty("ValidPassword"));
	loginpage.EnterPassword(dataprop.getProperty("ValidPassword"));
	
	//driver.findElement(By.xpath("//button[@type='submit']")).click();
	loginpage.ClickOnLoginButton();
	
	//String actualwarningmsg= driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
	String actualwarningmsg = loginpage.RetriveInvalidCredentialWarningMessage();
	
	String expectedwarningmsg= dataprop.getProperty("ExpectedWarningMessageOfLogin");
	
	Assert.assertTrue(actualwarningmsg.contains(expectedwarningmsg),"Expected warning msg is not displayed");
		
	}
	
	

	@Test(priority=3)
	public void verifyloginwithinvalidpassword() {
	LoginPage loginpage = new LoginPage(driver);
	
	//driver.findElement(By.xpath("//input[@name='username']")).sendKeys(dataprop.getProperty("ValidUserName"));
	loginpage.EnterUserName(dataprop.getProperty("ValidUserName"));
	
	//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataprop.getProperty("InvaliPassword"));
	loginpage.EnterPassword(dataprop.getProperty("InvaliPassword"));
	
	//driver.findElement(By.xpath("//button[@type='submit']")).click();
	loginpage.ClickOnLoginButton();
	
	//String actualwarningmsg= driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
	String actualwarningmsg = loginpage.RetriveInvalidCredentialWarningMessage();
	
	String expectedwarningmsg= dataprop.getProperty("ExpectedWarningMessageOfLogin");
	Assert.assertTrue(actualwarningmsg.contains(expectedwarningmsg),"Expected warning msg is not displayed");
		
	}
	
	@Test(priority=4)
	public void verifyloginwithinvaliusernameandpw() 
	{
		LoginPage loginpage = new LoginPage(driver);
		
		//driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Utility.generatetimestampusername());
		loginpage.EnterUserName(Utility.generatetimestampusername());
		
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataprop.getProperty("InvaliPassword"));
		loginpage.EnterPassword(dataprop.getProperty("InvaliPassword"));
		
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		loginpage.ClickOnLoginButton();
		
		//String actualwarningmsg= driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		String actualwarningmsg = loginpage.RetriveInvalidCredentialWarningMessage();
		
		String expectedwarningmsg= dataprop.getProperty("ExpectedWarningMessageOfLogin");
		Assert.assertTrue(actualwarningmsg.contains(expectedwarningmsg),"Expected warning msg is not displayed");
	}
	
	
	
	
}
