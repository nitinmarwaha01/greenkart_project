package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class LoginPage extends BaseClass {
	

	public LoginPage(WebDriver driver) throws IOException
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='email_create']")
	private WebElement txtEmailAddressForCreateAccount;
	
	@FindBy(xpath = "//button[@id='SubmitCreate']")
	private WebElement btnCreateAnAccount;
	
	public WebElement txtEmailAddressForCreateAccount()
	{
		return txtEmailAddressForCreateAccount;
	}
	
	public WebElement btnCreateAnAccount()
	{
		return btnCreateAnAccount;
	}
	
}
