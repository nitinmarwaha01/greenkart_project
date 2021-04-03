package uiScreens;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.BaseClass;
import utilities.CommonFunctions;
import utilities.Reporting;

public class RegisterationPage extends BaseClass {
	
	public RegisterationPage(WebDriver driver){
		this.driver = driver;
	}
	
	private Logger log = LogManager.getLogger(RegisterationPage.class.getName());

	public boolean createAccount(Map<String, String> oMap) throws IOException {
		
		boolean bFlag=true;
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		CommonFunctions common = new CommonFunctions();
		AccountRegisterationPage registerationPage = new AccountRegisterationPage(driver);
		Reporting oReport = new Reporting(driver);
		
			log.info("***************************Test case Started***************************");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.btnSignIn()));
			if(homePage.btnSignIn().isDisplayed()) {			
				log.info("User logged in successfully.");
				oReport.reporter("Login to application", "PASS", true);
			}
			else {
				log.fatal("User failed to login into application.");
				oReport.reporter("Failed to login into application", "FAIL", true);
			}
			
			homePage.btnSignIn().click();
				log.debug("User clicked on Sign In button.");
				oReport.reporter("Click on Sign In Button", "INFO", false);
				
			wait.until(ExpectedConditions.elementToBeClickable(loginPage.txtEmailAddressForCreateAccount()));
			if(loginPage.txtEmailAddressForCreateAccount().isDisplayed()) {
				log.info("User navigated to Registeration page successfully.");
				oReport.reporter("User navigated to Registeration page successfully.", "PASS", true);
			}
			else {
				log.fatal("User failed to navigate to registeration page.");
				oReport.reporter("User failed to navigate to registeration page.", "FAIL", true);
				return false;
			}
			String email = oMap.get("First_Name") + "." + oMap.get("Last_Name")
			+ common.generateRandomNumbers() + "@abc.com";
			loginPage.txtEmailAddressForCreateAccount().sendKeys(email);
				log.debug("user entered the email - " + email + ".");
				oReport.reporter("User entered email - " + email, "INFO", false);
			loginPage.btnCreateAnAccount().click();
				log.debug("User clicked on Create An Account button.");
				oReport.reporter("User clicked on Create An Account button.", "INFO", true);
			wait.until(ExpectedConditions.visibilityOf(registerationPage.txtYourPersonalInformation()));
			if(registerationPage.txtYourPersonalInformation().isDisplayed()) {
				log.info("User navigated to Personal details successfully.");
				oReport.reporter("User navigated to Personal details successfully.", "PASS", true);
			}
			else {
				log.fatal("User failed to navigate to Personal details page.");
				oReport.reporter("User navigated to Personal details successfully.", "FAIL", true);
				return false;
			}
			registerationPage.selectTitle("Mr");
				log.debug("Title is selected as - Mr.");
				oReport.reporter("Title is selected as - Mr.", "INFO", false);
			registerationPage.txtFirstName().sendKeys(oMap.get("First_Name"));
				log.debug("User entered First Name - " + oMap.get("First_Name") + ".");
				oReport.reporter("User entered First Name - " + oMap.get("First_Name") + ".", "INFO", false);
			registerationPage.txtLastName().sendKeys(oMap.get("Last_Name"));
				log.debug("User entered Last Name - " + oMap.get("Last_Name") + ".");
				oReport.reporter("User entered Last Name - " + oMap.get("Last_Name") + ".", "INFO", false);
			registerationPage.txtPassword().sendKeys(oMap.get("Password"));
				log.debug("User entered password - " + oMap.get("Password") + ".");
				oReport.reporter("User entered password - " + oMap.get("Password") + ".", "INFO", false);
			registerationPage.setDateOfBirth(oMap.get("DOB"));
				log.debug("User entered Date of Birth - " + oMap.get("DOB"));
				oReport.reporter("User entered Date of Birth - " + oMap.get("DOB") + ".", "INFO", false);
			registerationPage.txtAddressLine1().sendKeys(oMap.get("Address_Line1"));
				log.debug("User entered Address Line1 - " + oMap.get("Address_Line1") + ".");
				oReport.reporter("User entered Address Line1 - " + oMap.get("Address_Line1") + ".", "INFO", false);
			registerationPage.txtCity().sendKeys(oMap.get("City"));
				log.debug("User entered City - " + oMap.get("City") + ".");
				oReport.reporter("User entered City - " + oMap.get("City") + ".", "INFO", false);
			common.selectVisibleTextInDropdown(registerationPage.txtState(), oMap.get("State"));
				log.debug("User selected State - " + oMap.get("State") + ".");
				oReport.reporter("User selected State - " + oMap.get("State") + ".", "INFO", false);
			registerationPage.txtPostCode().sendKeys(oMap.get("Post_Code"));
				log.debug("User selected ZipCode - " + oMap.get("Post_Code") + ".");
				oReport.reporter("User selected ZipCode - " + oMap.get("Post_Code") + ".", "INFO", false);
			registerationPage.txtAddressAlias().clear();
			registerationPage.txtMobilePhone().sendKeys(oMap.get("Mobile"));
				log.debug("User entered mobile No - " + oMap.get("Mobile") + ".");
				oReport.reporter("User entered mobile No - " + oMap.get("Mobile") + ".", "INFO", false);
			registerationPage.txtAddressAlias().sendKeys(oMap.get("Alias"));
				log.debug("User entered alias - " + oMap.get("Alias") + ".");
				oReport.reporter("User entered alias - " + oMap.get("Alias") + ".", "INFO", false);
			registerationPage.btnRegister().click();
			wait.until(ExpectedConditions.visibilityOf(registerationPage.txtAccountCreated()));
			if(registerationPage.txtAccountCreated().isDisplayed()) {
				log.info("Account created successfully - " + email + ".");
				oReport.reporter("Account created successfully - " + email + ".", "PASS", true);
				bFlag = true;
			}else{
				log.fatal("Account creation failed.");	
				oReport.reporter("Account creation failed.", "FAIL", true);
				bFlag = false;
			}
			log.info("***************************Test case finished***************************");
			
		return bFlag;
	}
}
