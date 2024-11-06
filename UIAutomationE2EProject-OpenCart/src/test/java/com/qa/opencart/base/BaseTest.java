package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CheckOutPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;
/**
 * @author Veena Hegde
 */
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected RegisterPage registerPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckOutPage checkOutPage;
	
	@Parameters({"browser","browserversion","testname"}) 
	@BeforeTest
	
	public void setUp(@Optional String browserName,@Optional String browserVersion, @Optional String testName ) { 
		df = new DriverFactory();
		prop = df.initProp();
		//check if browser parameter is coming from testng.xml
		if(browserName != null) { 
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		driver = df.initDriver(prop);		
		loginPage = new LoginPage(driver); 
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}
	
	/**
	 * This method is used to open the Extent & Allure report after the test run in local.
	 */
//	@AfterSuite
//	public void openHtmlReport() {
//		// extent html report
//		try {
//			File htmlFile = new File("reports/TestExecutionReport.html");
//			if (htmlFile.exists()) {
//				Desktop.getDesktop().browse(htmlFile.toURI());
//			} else {
//				System.out.println("Report file not found: " + htmlFile.getAbsolutePath());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// allure
//		try {
//			// Serve the Allure report
//			ProcessBuilder builder = new ProcessBuilder("C:\\Users\\aksha\\scoop\\apps\\allure\\2.30.0\\bin\\allure.bat", "serve", "allure-results");
//			builder.inheritIO();
//			Process process = builder.start();
//			process.waitFor();
//
//			// The `allure serve` command automatically opens the report in a browser.
//			System.out.println("Allure report served successfully.");
//
//		} catch (IOException | InterruptedException e) {
//			e.printStackTrace();
//			System.out.println("Failed to serve Allure report.");
//		}
//
//	}

}
