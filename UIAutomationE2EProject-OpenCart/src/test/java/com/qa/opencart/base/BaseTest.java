package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected RegisterPage registerPage;
	protected ResultsPage resultsPage;
	
	@BeforeTest
	
	public void setUp() { 
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);		
		loginPage = new LoginPage(driver); 
	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}

}
