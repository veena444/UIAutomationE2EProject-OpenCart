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
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	/**
	 * @author Veena Hegde
	 */
	WebDriver driver;
	Properties prop;
	
	public static String isHighlight;
	
	/**
	 * This method is used to initialize the driver on the basis of given browser name.
	 * @param browserName
	 * @return It returns driver.
	 */
	public WebDriver initDriver(Properties prop) { 

		String browserName = prop.getProperty("browser");  
		System.out.println("browser name is: "+browserName);
		
		isHighlight = prop.getProperty("highlight");
		
		OptionsManager optionsManager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
//			driver = new ChromeDriver();
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
//			driver = new FirefoxDriver();
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
//			driver = new EdgeDriver();
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
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
/*
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
*/
	
	/**
	 * This method is used to run the test cases for different environments using respective config.properties file.  
	 * @return It returns prop.
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		System.out.println("Running tests on env: "+envName);
		try {
			if(envName == null) {
				System.out.println("env is null..hence running tests on QA env");
				ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
			}
			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
					break;

				default:
					System.out.println("Please pass the right environment: "+envName);
					throw new FrameworkException("INVALID ENV NAME");
				}
			}
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;	
	}


}
