package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class HomePage extends BaseClass {
	

	public HomePage(WebDriver driver) throws IOException 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "a.login")
	private WebElement btnSignIn;
	
	public WebElement btnSignIn()
	{
		return btnSignIn;
	}
}
