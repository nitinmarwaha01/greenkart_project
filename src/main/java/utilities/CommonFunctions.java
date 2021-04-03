package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions extends BaseClass{

	private static Logger log = LogManager.getLogger(CommonFunctions.class);
	
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
		if(element.getText().equals(visibleText)) {
			log.info("Visible text selected successfully.");
		}else
			log.warn("Failed to select visible text");
	}
	
	public void selectItemByValue(WebElement element, String textToSelect){
		Select selectList = new Select(element);
		selectList.selectByValue(textToSelect.trim());
		log.info("Text " + textToSelect + " is selected in dropdown.");
	}

}
