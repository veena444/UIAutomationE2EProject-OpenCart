package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
	
	@Parameters({"browser"}) 
	@BeforeTest
	
	public void setUp(String browserName) { 
		df = new DriverFactory();
		prop = df.initProp();
		//check if browser parameter is coming from testng.xml
		if(browserName != null) { 
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);		
		loginPage = new LoginPage(driver); 
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}

}
