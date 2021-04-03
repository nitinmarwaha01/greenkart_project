package utilities;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testing {

	ExcelDataProvider excel = new ExcelDataProvider();
	String sheetName = "Account_Details";
	
	@Test(dataProvider="Data")
	public void test1(Map<String, String> oMap)
	{
		System.out.println(oMap);
	}
	
	@DataProvider(name="Data")
	public Object[][] getData() {
		Object[][] data = null;
		int selectedTestCases=0;
		
		System.out.println(excel.getRowCount(sheetName)+1);
		for(int rowCounter=1;rowCounter<=excel.getRowCount(sheetName);rowCounter++) {
			if(excel.getCellValueBasedOnColName(sheetName, rowCounter, "Flag").equalsIgnoreCase("Y")) {
				selectedTestCases++;
			}
		}
		
System.out.println(selectedTestCases);
		
		data = new Object[selectedTestCases][1];
		
		for(int rowCounter1=1;rowCounter1<=excel.getRowCount(sheetName);rowCounter1++) {
			Map<String, String> map = new HashMap<String, String>();
			if(excel.getCellValueBasedOnColName(sheetName, rowCounter1, "Flag").equalsIgnoreCase("Y")) {
				map = excel.getRowData(sheetName, rowCounter1);
				data[rowCounter1-1][0] = map;
			}
		}
		
		return data;
	}
	
}
