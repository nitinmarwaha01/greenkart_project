package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory extends BaseClass
{
	
	public WebDriver initializeDriver(String browserName) {
				
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("headless")) {
			System.setProperty("webdriver.chrome.driver", "./resources/drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		return driver;
	}
	
	public void startApplication(String appURL) {
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(IWaitTimeConstants.MINIMUM_TIME, TimeUnit.SECONDS);
	}
	
}
