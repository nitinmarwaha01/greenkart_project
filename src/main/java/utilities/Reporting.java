package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;

public class Reporting extends BaseClass {
	
	public Reporting(WebDriver driver) {
		this.driver=driver;
	}
			
	public void reporter(String message, String status, boolean screenshotFlag) throws IOException {
		if(status.equalsIgnoreCase("PASS")) {
			if(screenshotFlag == true) 
				extentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
			else
				extentTest.pass(message);
		}else if(status.equalsIgnoreCase("FAIL")) {
			if(screenshotFlag == true) 
				extentTest.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
			else
				extentTest.fail(message);
		}else if(status.equalsIgnoreCase("INFO")) {
			if(screenshotFlag == true)
				extentTest.info(message, MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
			else
				extentTest.info(message);
		}	
	}
}
