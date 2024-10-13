package orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
	WebDriver driver;
	
	@FindBy(xpath="//span[@class='oxd-topbar-header-breadcrumb']")
	private WebElement AdminPage;



public AdminPage(WebDriver driver) 
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

public String RetriveActualAdminPageText() 
{
	String ActualAdminPageText = AdminPage.getText();
	return ActualAdminPageText;
}


}