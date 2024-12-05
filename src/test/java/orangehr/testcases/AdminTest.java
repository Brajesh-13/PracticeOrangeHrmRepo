package orangehr.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.baseclass.Base;
import orangehrm.pages.AdminPage;
import orangehrm.pages.DashboardPage;
import orangehrm.pages.LoginPage;

public class AdminTest extends Base {
	public WebDriver driver;
	
	//Added the comment just to check.
	
	@BeforeMethod
	public void Setup() 
	{
	loadpropertiesfile();
	driver=initializebrowsserandopenurl(prop.getProperty("browserName"));
	
	LoginPage loginpage = new LoginPage(driver);
	loginpage.EnterUserName(dataprop.getProperty("ValidUserName"));
	loginpage.EnterPassword(dataprop.getProperty("ValidPassword"));
	loginpage.ClickOnLoginButton();
	
	/*driver.findElement(By.xpath("//input[@name='username']")).sendKeys(dataprop.getProperty("ValidUserName"));
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataprop.getProperty("ValidPassword"));
	driver.findElement(By.xpath("//button[@type='submit']")).click();*/
	
	}
	
	
	
	//After each test method this AfterMethod will get executed
	@AfterMethod
	
	public void tearDown() {
	//if we don't use after method separately then if any test method will fail browser will not close automatically
	driver.quit();
	}
	
	
	@Test(priority=1)
	public void SearchAdminInSearchBox() 
	
	{
		DashboardPage dashboardpage = new DashboardPage(driver);
		//Assert.assertTrue(driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).isDisplayed(),"Dasboard is not displayed");
		Assert.assertTrue(dashboardpage.VerifyDasboardPageAfterLogin(),"Dasboard is not displayed");
		
		//not needed below line
		//driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/child::ul/child::li[1]")).click();
		
		
		//driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active']")).sendKeys("Admin");
		dashboardpage.EnterAdminInSearchBox("Admin");
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    //List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='oxd-main-menu']/child::li")));
		// Using Locator from Dashboard page Locaotor here 
		By Adminnenulocator = dashboardpage.getAdminMenuAppearanceLocator();
	    List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Adminnenulocator));
	    
	    // Click on the desired suggestion
       for (WebElement suggestion : suggestions) {
           if (suggestion.getText().contains("Admin")) {
        	   suggestion.click();
               break;
           }
       }
		
	}
	
	
	@Test(priority=2)
	public void VerifyAdminPage() throws InterruptedException 
	{
	DashboardPage dashboardpage = new DashboardPage(driver);
	AdminPage adminpage = new AdminPage(driver);
		
	//driver.findElement(By.xpath("//ul[@class='oxd-main-menu'][1]//child::li[1]")).click();
	dashboardpage.ClickOnAdminButton();
	
	//String ActualAdminPageText =driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']")).getText();
	String ActualAdminPageText = adminpage.RetriveActualAdminPageText();
	
	System.out.println(ActualAdminPageText);
	String ExpectedAdminPageText = dataprop.getProperty("ExpectedAdminPageText");
	Assert.assertEquals(ExpectedAdminPageText, ExpectedAdminPageText,"Expected admin page text is not displayed");
	
	}
	
	//@Test(priority=2)
	//public void Adminuseraddition()
	/*{ 
		//driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).click();
		//driver.findElement(By.xpath("//div[@class='orangehrm-background-container']/child::div[2]/child::div/child::button")).click();
		WebElement userroledropdown = driver.findElement(By.xpath("//div[@class='oxd-form-row']/child::div/child::div[1]"));
		Select userdropdown = new Select(userroledropdown);
		userdropdown.selectByIndex(0);
		driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("joker john selvam");
		WebElement statusdropdown = driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]/child::div/child::div[2]"));
		Select stsdropdown= new Select(statusdropdown);
		stsdropdown.selectByIndex(0);
		driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]/child::div/child::div[2]")).sendKeys("brajesh");
		driver.findElement(By.xpath("//div[@class='oxd-form-row user-password-row']//child::div/child::div[1]/child::div/child::div/child::input")).sendKeys("abc@123");
		driver.findElement(By.xpath("//div[@class='oxd-form-row user-password-row']/child::div/child::div[2]/child::div/child::div[2]/child::input")).sendKeys("abc@123");
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
	}*/
	

}
