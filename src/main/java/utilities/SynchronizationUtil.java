package utilities;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SynchronizationUtil extends BaseClass {

	private WebDriverWait wait;
	
	public SynchronizationUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, IWaitTimeConstants.MINIMUM_TIME);
	}

	public void synchronizeUntilElementIsInvisible
	(final WebElement element) throws TimeoutException {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void synchronizeUntilElementIsVisible
	(final WebElement element) throws TimeoutException {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void synchronizeUntilObjectIsClickable(final WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void synchronizeUntilTitleContains(final String strTitle) {
		wait.until(ExpectedConditions.titleContains(strTitle));
	}
	
	public void synchroniseUntilTheElementPopulateValues
	(WebElement element, final String strText) {
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, strText));
	}
	
	public void synchronizeUntilTheElementPopulateAttributeValue
	(WebElement element, final String attributeName, final String attributeValue) {
		wait.until(ExpectedConditions.attributeToBe(element, attributeName, attributeValue));
	}
	
	public void synchronizeUntilFrameIsAvailableAndSwitchToIt
	(WebElement frameLocator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void synchronizeUntilElementToBeSelected(WebElement element) {
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}
	
	public void synchronizeUntilAlertIsPresent() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void synchronizeUntilTheElementContainsAttributeValue
	(WebElement element, final String attributeName, final String attributeValue){
		wait.until(ExpectedConditions.attributeContains(element, attributeName, attributeValue));
	}
}
