package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {
	
	/**
	 * @author Veena Hegde
	 */
	WebDriver driver;
	Properties prop;
	
	/**
	 * This method is used to initialize the driver on the basis of given browser name.
	 * @param browserName
	 * @return It returns driver.
	 */
	public WebDriver initDriver(Properties prop) { 

		String browserName = prop.getProperty("browser");  
		System.out.println("browser name is: "+browserName);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println(AppError.INVALID_BROWSER_MESG + browserName + "is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MESG + browserName);

		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
/**
 * This method is used to initialize the properties from the config.properties file.
 * @return It returns prop
 */
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}


}
