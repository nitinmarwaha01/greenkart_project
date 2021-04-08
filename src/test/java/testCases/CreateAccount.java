package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import uiScreens.RegisterationPage;
import utilities.BaseClass;
import utilities.CommonFunctions;

public class CreateAccount extends BaseClass {
	
	String sheetName = "Account_Details";
	
	@Test(dataProvider = "Data")
	public void addAccount(Map<String, String> oMap) throws IOException {
	
	RegisterationPage registerationPage = new RegisterationPage(driver);
	if(registerationPage.createAccount(oMap)) {
		extentTest.pass("Test case passed.", MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
	}else {
		extentTest.fail("Test case failed.", MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.ScreenCapture(driver)).build());
	}
		
}
	
	@DataProvider(name="Data")
	public Object[][] getData() {
		Object[][] data = null;
		int selectedTestCases=0;
		int testCaseCounter=0;
		
		for(int rowCounter=1;rowCounter<=excel.getRowCount(sheetName);rowCounter++) {
			if(excel.getCellValueBasedOnColName(sheetName, rowCounter, "Flag").equalsIgnoreCase("Y")) {
				selectedTestCases++;
			}
		}
		
		data = new Object[selectedTestCases][1];
		
		for(int rowCounter1=1;rowCounter1<=excel.getRowCount(sheetName);rowCounter1++) {
			Map<String, String> map = new HashMap<String, String>();
			if(excel.getCellValueBasedOnColName(sheetName, rowCounter1, "Flag").equalsIgnoreCase("Y")) {
				map = excel.getRowData(sheetName, rowCounter1);
				data[testCaseCounter][0] = map;
				testCaseCounter++;
			}
		}
		
		return data;
	}
}
