package orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	WebDriver driver;
	@FindBy(xpath="//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	private WebElement DashboardVerificationText;
	
	@FindBy(xpath="//i[@class='oxd-icon bi-question-lg']")
	private WebElement Dashboardhelp;
	
	@FindBy(xpath="//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-quick-launch-card'][1]")
	private WebElement Quciklaunch;
	
	@FindBy(xpath="//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	private WebElement Actualleave;
	
	@FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
	private WebElement Serachbox;
	
	
	@FindBy(xpath="//ul[@class='oxd-main-menu']/child::li")
	 private WebElement Adminmenuappearance;
	
	@FindBy(xpath="//ul[@class='oxd-main-menu'][1]//child::li[1]")
	private WebElement Admin;
	
	
	
	
	public DashboardPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	
	//Actions 
	
	public boolean VerifyDasboardPageAfterLogin() 
	{
		boolean displayeddashboardstatus = DashboardVerificationText.isDisplayed();
		return displayeddashboardstatus;
	}
	
	public void Dashboardhelpbutton() 
	{
		Dashboardhelp.click();
	}
	
	public void Quicklaunchbutton() 
	{
		Quciklaunch.click();
	}
	
	public String RetriveActualLeaveText() 
	{
		String Actualleavemsg = Actualleave.getText();
		return Actualleavemsg;
	}
	
	public void EnterAdminInSearchBox(String SearchText) 
	{
		Serachbox.sendKeys(SearchText);
		
	}
	
	// Method to return the locator for the WebElement
    public By getAdminMenuAppearanceLocator() 
    {
      return By.xpath("//ul[@class='oxd-main-menu']/child::li");
    }
    
    
    public void ClickOnAdminButton() 
    {
    	Admin.click();
    }


}
