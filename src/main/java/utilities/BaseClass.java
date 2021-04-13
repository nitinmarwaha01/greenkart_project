package utilities;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	
	public BrowserFactory browserFactory;
	public CommonFunctions common;
	public ConfigDataProvider config;
	public ExcelDataProvider excel;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public WebDriver driver;
	public static WebDriverWait wait;
	public static Logger log;
	
	@BeforeSuite
	public void setUpSuite() {
		log = LogManager.getLogger(BaseClass.class);
		config = new ConfigDataProvider();
		excel = new ExcelDataProvider();
		browserFactory = new BrowserFactory();
		extentReport = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(new File(System.getProperty("user.dir") 
				+ "/reports/MyAutomationReport_" + CommonFunctions.getCurrentDateTime() + ".html"));
		htmlReport.config().setDocumentTitle("Test Automation Report");
		htmlReport.config().setReportName("Automation Practise");
		extentReport.attachReporter(htmlReport);
		extentTest = extentReport.createTest("GreenKart");
	}
	
	@BeforeMethod
	public void initializeApplication() {
		driver = browserFactory.initializeDriver(config.getBrowser());
		wait = new WebDriverWait(driver, IWaitTimeConstants.MINIMUM_TIME);
		browserFactory.startApplication(config.getApplicationURL());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
	
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel("Test case passed", ExtentColor.GREEN));
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel("Test case failed", ExtentColor.RED));
		}
		
		driver.quit();
		extentReport.flush();
	}

}
