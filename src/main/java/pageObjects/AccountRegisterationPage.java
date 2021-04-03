package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.BaseClass;

public class AccountRegisterationPage extends BaseClass {

	public AccountRegisterationPage(WebDriver driver) throws IOException
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h3[text()='Your personal information']")
	private WebElement txtYourPersonalInformation;
	
	@FindBys({
        @FindBy(name = "id_gender")
	})
	private List<WebElement> radioBtnTitle;
	
	@FindBys({
        @FindBy(xpath = "//label[contains(@for,'id_gender')]")
	})
	List<WebElement> labelTitle;
	
	@FindBy(id = "customer_firstname")
	private WebElement txtFirstName;
	
	@FindBy(id = "customer_lastname")
	private WebElement txtLastName;
	
	@FindBy(id = "passwd")
	private WebElement txtPassword;
	
	@FindBy(id = "days")
	private WebElement selectDay;
	
	@FindBy(id = "months")
	private WebElement selectMonth;
	
	@FindBy(id = "years")
	private WebElement selectYear;
	
	@FindBy(id = "address1")
	private WebElement txtAddressLine1;
	
	@FindBy(id = "city")
	private WebElement txtCity;
	
	@FindBy(id = "id_state")
	private WebElement txtState;
	
	@FindBy(id = "postcode")
	private WebElement txtPostCode;
	
	@FindBy(id = "phone_mobile")
	private WebElement txtMobilePhone;
	
	@FindBy(id = "alias")
	private WebElement txtAddressAlias;
	
	@FindBy(xpath = "//span[text()='Register']")
	private WebElement btnRegister;
	
	@FindBy(xpath = "//p[contains(text(),'Welcome')]")
	private WebElement txtAccountCreated;
	
	public WebElement txtYourPersonalInformation(){
		return txtYourPersonalInformation;}
	
	public WebElement txtFirstName(){
		return txtFirstName;}
	
	public WebElement txtLastName(){
		return txtLastName;}
	
	public WebElement txtPassword(){
		return txtPassword;}
	
	public WebElement txtAddressLine1(){
		return txtAddressLine1;}
	
	public WebElement txtState(){
		return txtState;}
	
	public WebElement txtCity(){
		return txtCity;}
	
	public WebElement txtPostCode(){
		return txtPostCode;}
	
	public WebElement txtMobilePhone(){
		return txtMobilePhone;}
	
	public WebElement btnRegister(){
		return btnRegister;}
	
	public WebElement txtAddressAlias(){
		return txtAddressAlias;}
	
	public WebElement txtAccountCreated(){
		return txtAccountCreated;}
	
	public void setDateOfBirth(String dob)
	{
		String[] dateOfBirth;
		
		if (dob.contains("/"))
			dateOfBirth = dob.split("/");
		else if(dob.contains("_"))
			dateOfBirth = dob.split("_");
		else
			dateOfBirth = dob.split(".");
		
		WebElement[] arrWebElement = new WebElement[3];
		arrWebElement[0] = selectDay;
		arrWebElement[1] = selectMonth;
		arrWebElement[2] = selectYear;
		for(int counter=0; counter<arrWebElement.length;counter++)
		{
			Select selectList = new Select(arrWebElement[counter]);
			selectList.selectByValue(dateOfBirth[counter]);
		}
	}
	
	public void selectTitle(String optionToSelect)
	{
		String option = optionToSelect.trim();
		for(int counter=0;counter<labelTitle.size();counter++)
		{
			if(labelTitle.get(counter).getText().contains(option))
			{
				radioBtnTitle.get(counter).click();
				break;
			}
		}
	}
}
