package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uiScreens.GreenKartPage;
import utilities.BaseClass;

public class GreenKart extends BaseClass{

	String sheetName = "Vegetables";  
	
	@Test(dataProvider = "testdata")
	public void addToCartFunctionality(Map<String, String> oMap) throws TimeoutException {
		GreenKartPage greenKart = new GreenKartPage(driver);
		log.info("**********************Test case 'Add to cart functionality' execution started**********************");
		try {
			greenKart.addVegetablesToKart(oMap);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("**********************Test case 'Add to cart functionality' execution ended**********************");
	}
	
	@DataProvider(name="testdata")
	public Object[][] getData(){
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
