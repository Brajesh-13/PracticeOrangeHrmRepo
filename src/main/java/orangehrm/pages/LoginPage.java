package orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	//LStoring webElement by Page Factory Method
	//Made all the WebElement Private so can Access with in the class 
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement Username;
	
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement Password;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement LoginButton;
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	private WebElement InvalidCredentialWarningMsg;
	
	
	//Creating Constructor 
	
	public LoginPage(WebDriver driver) 
	{
		//saying global driver equal to parameter driver
		//whenever the object of LoginPage created this constructor will be called 
		// & it takes value from login class and store in this parameterize driver and then it will pass value to global driver
		this.driver = driver;
		// below line  will initialize all the WebElement of LoginPage class result of this it will avoid stale element exception
		
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions : Means Doing all the action here only like passing username & password . Also Click on Login button etc.
	
	
	public void EnterUserName(String UserNameText) 
	{
		Username.sendKeys(UserNameText);
	}
	
	
	public void EnterPassword(String PasswordText) 
	{
		Password.sendKeys(PasswordText);
	}
	
	public void ClickOnLoginButton() 
	{
		LoginButton.click();
	}

	
	public String RetriveInvalidCredentialWarningMessage() 
	{
		String Warningtext = InvalidCredentialWarningMsg.getText();
		return Warningtext;
	}
}
