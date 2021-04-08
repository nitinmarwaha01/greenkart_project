package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions extends BaseClass{
	
	public static String ScreenCapture(WebDriver driver) {
		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir") + "/screenshots/Screenshot" + getCurrentDateTime() + ".png";
		File destFile = new File(destFilePath);
		try {
			FileUtils.copyFile(srcFile, destFile);
			log.info("Screenshot taken successfully.");
		} catch (IOException e) {
			log.warn("Failed to take screenshot. " + e.getMessage());
		}
		return destFilePath;
	}
	
	public int generateRandomNumbers()
	{
		Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(1000);
        return randomNumber;
	}
	
	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_YYYY_HH_mm_ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public void selectVisibleTextInDropdown(WebElement element, String visibleText) {
		Select dropdown = new Select(element);
		
		dropdown.selectByVisibleText(visibleText);
		log.info("Visible text selected successfully.");
	}
	
	public void selectItemByValue(WebElement element, String textToSelect){
		Select selectList = new Select(element);
		selectList.selectByValue(textToSelect.trim());
		log.info("Text " + textToSelect + " is selected in dropdown.");
	}

	public void highlightWebElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid black;');", element);
	}
	
	public void moveToWebElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void rightClickOnWebElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	public void doubleClickOnWebElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	public void dragAndDrop(WebDriver driver, WebElement srcElement, WebElement destElement) {
		Actions action = new Actions(driver);
		action.dragAndDrop(srcElement, destElement).perform();
	}
	
	public void clickUsingJavascript(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void sendKeysUsingJavascript(WebDriver driver, String elementID, String strText) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + elementID + "').value='" + strText + "';");
	}
	
	public void clickCheckboxUsingJavascript(WebDriver driver, String elementID, boolean enableSwitch) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + elementID + "').checked='" + enableSwitch + "';");
	}
	
	public String getPageSource(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String pageSource = js.executeScript(" return document.documentElement.innerText;").toString();
		return pageSource;
	}
	
	public void keyPress(WebDriver driver, WebElement Element, String keyToPress) {
		Actions action = new Actions(driver);
		switch(keyToPress.toLowerCase()) {
			case "enter":
				action.sendKeys(Keys.ENTER).build().perform();
				break;
			case "pageup":
				action.sendKeys(Keys.PAGE_UP).build().perform();
				break;
			case "pagedown":
				action.sendKeys(Keys.PAGE_DOWN).build().perform();
				break;
			case "clear":
				action.sendKeys(Keys.CLEAR).build().perform();
				break;
			case "tab":
				action.sendKeys(Keys.TAB).build().perform();
				break;
		}
	}
}
