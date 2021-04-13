package testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utilities.BaseClass;
import utilities.Reporting;

public class validateBrokenLinks extends BaseClass {
	
	@Test
	public void validateLinks() throws MalformedURLException, IOException {
		Reporting oReport = new Reporting(driver);
		
		log.info("**********************Test case 'Validate Broken Links' execution started**********************");
		WebElement footerTable = driver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody"));
		List<WebElement> allLinks = footerTable.findElements(By.tagName("a"));
		for(WebElement link : allLinks) {
			String url = link.getAttribute("href");
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();
			log.info("Response for link '" + link.getText() + "' is " + responseCode);
			oReport.reporter("Response for link '" + link.getText() + "' is " + responseCode, "INFO", false);
		}
		log.info("**********************Test case 'Validate Broken Links' execution ended**********************");
	}

}
