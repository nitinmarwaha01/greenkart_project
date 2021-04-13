package uiScreens;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.GreenKart_POM;
import utilities.BaseClass;
import utilities.Reporting;
import utilities.SynchronizationUtil;

public class GreenKartPage extends BaseClass {
	
	public GreenKartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addVegetablesToKart(Map<String, String> oMap) throws TimeoutException, IOException {
		GreenKart_POM greenKart = new GreenKart_POM(driver);
		SynchronizationUtil sync = new SynchronizationUtil(driver);
		Reporting oReport = new Reporting(driver);
		
		String vegetableDetails = oMap.get("Vegetables");
		List<String> vegetables = Arrays.asList(vegetableDetails.split(","));
		List<WebElement> productNames = greenKart.getProductNames();
		List<WebElement> addToCart = greenKart.getAddToCartLinks();
		List<WebElement> veggiesQuantity = greenKart.getVegetablesQuantity();
		
		for(int i=0;i<vegetables.size();i++)
		{
			String vegetableName = vegetables.get(i).replace("$", " ").split(" ")[0];
			String vegetableQuantity = vegetables.get(i).replace("$", " ").split(" ")[1];
			for(int j=0;j<productNames.size();j++) {
				String productName = productNames.get(j).getText().toString().split("-")[0].trim();
				if(productName.equals(vegetableName)) {
					veggiesQuantity.get(j).clear();
					veggiesQuantity.get(j).sendKeys(vegetableQuantity);
					log.info("Entering quantity to " + vegetableQuantity);
					oReport.reporter("Entering quantity to " + vegetableQuantity, "INFO", false);
					addToCart.get(j).click();
					log.info("Clicking on 'ADD TO CART' button for vegetable " + vegetableName);
					oReport.reporter("Clicking on 'ADD TO CART' button for vegetable " + vegetableName, "INFO", true);
				}
			}
		}
		greenKart.cartIcon().click();
		log.info("Clicking on Cart icon.");
		oReport.reporter("Clicking on Cart icon.", "INFO", false);
		sync.synchronizeUntilElementIsVisible(greenKart.proceedToCheckOut());
		greenKart.proceedToCheckOut().click();
		log.info("Clicking on 'PROCEED TO CHECKOUT' button.");
		oReport.reporter("Clicking on 'PROCEED TO CHECKOUT' button.", "INFO", false);
		sync.synchronizeUntilElementIsVisible(greenKart.placeOrder());
		if(greenKart.placeOrder().isDisplayed()) {
			log.info("User navigated to CHECKOUT page successfully.");
			oReport.reporter("User navigated to CHECKOUT page successfully.", "PASS", true);
		}else {
			log.fatal("User failed to navigate to CHECKOUT page.");
			oReport.reporter("User failed to navigate to CHECKOUT page.", "FAIL", true);
			return;
		}
		greenKart.placeOrder().click();
		log.info("Clicking on Place order button.");
		oReport.reporter("Clicking on Place order button.", "INFO", false);
		if(greenKart.proceed().isDisplayed()) {
			log.info("User navigated to Terms and Conditions page successfully.");
			oReport.reporter("User navigated to Terms and Conditions page successfully.", "PASS", true);
		}else {
			log.fatal("User failed to navigate to Terms and Conditions page.");
			oReport.reporter("User failed to navigate to Terms and Conditions page.", "FAIL", true);
			return;
		}
		greenKart.Country().selectByVisibleText("India");
		log.info("India is selected in Country dropdown.");
		oReport.reporter("India is selected in Country dropdown.", "INFO", false);
		greenKart.agreeTermsAndConditions().click();
		log.info("Checked Terms and Conditions checkbox.");
		oReport.reporter("Checked Terms and Conditions checkbox.", "INFO", true);
		greenKart.proceed().click();
		log.info("Clicking on Proceed button.");
		oReport.reporter("Clicking on Proceed button.", "INFO", false);
		if(greenKart.cartIcon().isDisplayed()) {
			log.info("Add vegetables to Cart is verified successfully.");
			oReport.reporter("Add vegetables to Cart is verified successfully.", "PASS", true);
		}else {
			log.fatal("Add vegetables to Cart functionality is not verified.");
			oReport.reporter("Add vegetables to Cart functionality is not verified.", "FAIL", true);
		}
	}
}
