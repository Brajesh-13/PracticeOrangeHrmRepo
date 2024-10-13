package orangehr.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.baseclass.Base;
import orangehrm.pages.DashboardPage;
import orangehrm.pages.LoginPage;

public class DashboardTest extends Base{

	public WebDriver driver;
	
	
	@BeforeMethod
	public void Setup() throws InterruptedException 
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
	public void VerifyDashboardHelp() 
	{
		DashboardPage dashboardpage = new DashboardPage(driver);
		//driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']")).click();
		dashboardpage.Dashboardhelpbutton();
		String ActualTitle = driver.getTitle();
		System.out.println(ActualTitle);
		
		//ForceFully Failing here by passing wrong expected title
		Assert.assertEquals(ActualTitle,"OrangeHRMTest","Title is not matching");
	}
	
	//ForceFully Skipping this method as first method is failing 
	@Test(priority=2,dependsOnMethods = "VerifyDashboardHelp")
	
	public void QuickLaunchAssignLeave() 
	{
		DashboardPage dashboardpage = new DashboardPage(driver);
		
		//driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-quick-launch-card'][1]")).click();
		dashboardpage.Quicklaunchbutton();
		
		//String ActualLeaveText = driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).getText();
		String ActualLeaveText = dashboardpage.RetriveActualLeaveText();
		
		System.out.println(ActualLeaveText);
		String ExpectedLeaveText = dataprop.getProperty("ExpectedLeaveText");
		Assert.assertEquals(ActualLeaveText, ExpectedLeaveText,"Expected Leave Page Text is not displayed");
	}
	
	/*@Test(priority=2)
	
	public void SearchInHelp() 
	{
		driver.findElement(By.id("query")).sendKeys("Mobile");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("query")));
	    
	    // Click on the desired suggestion
       for (WebElement suggestion : suggestions) {
           if (suggestion.getText().contains("Admin")) {
               suggestion.click();
               break;
           }
       }
	}*/
	
}
