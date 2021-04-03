package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import uiScreens.RegisterationPage;
import utilities.BaseClass;
import utilities.CommonFunctions;

public class CreateAccount extends BaseClass {
	
	private Logger log = LogManager.getLogger(CreateAccount.class.getName());
	String sheetName = "Account_Details";
	
	@Test(dataProvider = "Data")
	public void addAccount(Map<String, String> oMap) throws IOException {
	
	log.info("***************************Test case Started***************************");
	RegisterationPage registerationPage = new RegisterationPage(driver);
	if(registerationPage.createAccount(oMap)) {
		log.info("test case passed");
		extentTest.pass("Test case passed.", MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
	}else {
		extentTest.fail("Test case failed.", MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
	}
	
	log.info("***************************Test case finished***************************");
		
}
	
	@DataProvider(name="Data")
	public Object[][] getData() {
		Object[][] data = null;
		int selectedTestCases=0;
		
		for(int rowCounter=1;rowCounter<=excel.getRowCount(sheetName);rowCounter++) {
			if(excel.getCellValueBasedOnColName(sheetName, rowCounter, "Flag").equalsIgnoreCase("Y")) {
				selectedTestCases++;
			}
		}
		
		data = new Object[selectedTestCases][1];
		
		for(int rowCounter=1;rowCounter<=excel.getRowCount(sheetName);rowCounter++) {
			Map<String, String> map = new HashMap<String, String>();
			if(excel.getCellValueBasedOnColName(sheetName, rowCounter, "Flag").equalsIgnoreCase("Y")) {
				map = excel.getRowData(sheetName, rowCounter);
				data[rowCounter-1][0] = map;
			}
		}
		
		return data;
	}
}
