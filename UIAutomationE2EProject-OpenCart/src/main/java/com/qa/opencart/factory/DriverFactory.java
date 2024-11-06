package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;
/**
 * @author Veena Hegde
 */
public class DriverFactory {
	

	WebDriver driver;
	Properties prop;
	
	public static String isHighlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	OptionsManager optionsManager;
	
	
	/**
	 * This method is used to initialize the driver on the basis of given browser name.
	 * @param browserName
	 * @return It returns driver.
	 */
	public WebDriver initDriver(Properties prop) { 

		String browserName = prop.getProperty("browser");  
//		System.out.println("browser name is: "+browserName);
		Log.info(prop.getProperty("testname") + " and browser name is : " + browserName);
		
		isHighlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//run test cases on remote/container
				init_remoteDriver("chrome");
			}
			else {
				//run test cases in local
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));  //with ThreadLocal
			}
//			driver = new ChromeDriver();
//			driver = new ChromeDriver(optionsManager.getChromeOptions()); //without ThreadLocal

			break;
		case "firefox":
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//run test cases on remote/container
				init_remoteDriver("firefox");
			}
			else {
				//run test cases in local
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions())); //with ThreadLocal
			}
//			driver = new FirefoxDriver();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions()); //without ThreadLocal
		
			break;
		case "edge":
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//run test cases on remote/container
				init_remoteDriver("edge");
			}
			else {
				//run test cases in local
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions())); //with ThreadLocal
			}
//			driver = new EdgeDriver();
//			driver = new EdgeDriver(optionsManager.getEdgeOptions()); //without ThreadLocal
			
			break;

		default:
//			System.out.println(AppError.INVALID_BROWSER_MESG + browserName + "is invalid");
			Log.error(AppError.INVALID_BROWSER_MESG + browserName + " is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MESG + browserName);

		}
		
//		driver.manage().window().maximize();
		getDriver().manage().window().maximize();
//		driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));
		
//		return driver;
		return getDriver();
	}
	
	
	private void init_remoteDriver(String browserName) {
//		System.out.println("Running tests on Selenium Grid with browser : "+browserName);
		Log.info("Running tests on Selenium Grid with browser : \"+browserName");
		try {
		switch (browserName.toLowerCase().trim()) {
		case "chrome":			
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));			
			break;
		case "firefox":			
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));			
			break;
		case "edge":			
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getEdgeOptions()));			
			break;

		default:
//			System.out.println("Please pass the right remote browser name...");
			Log.info("Please pass the right remote browser name...");
			throw new BrowserException(AppError.INVALID_BROWSER_MESG + browserName);
		}
	 }
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * This method is returning the driver with ThreadLocal
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
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
//		System.out.println("Running tests on env: "+envName);
		Log.info("Running tests on env: "+envName);
		try {
			if(envName == null) {
//				System.out.println("env is null..hence running tests on QA env");
				Log.warn("env is null..hence running tests on QA env");
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
//					System.out.println("Please pass the right environment: "+envName);
					Log.error("Please pass the right environment: "+envName);
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

	/**
	 * This method is used to take the screenshot.
	 * @param methodName
	 * @return It returns path of the file.
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
