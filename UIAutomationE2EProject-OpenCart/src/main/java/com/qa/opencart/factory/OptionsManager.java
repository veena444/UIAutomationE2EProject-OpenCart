package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
				{
			System.out.println("...Running in headless...");
			co.addArguments("--headless");
			co.addArguments("--window-position=-2400,-2400"); //temporary fix
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			co.addArguments("--incognito");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
		}
		
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("...Running in headless...");
			fo.addArguments("--headless");
			fo.addArguments("--window-position=-2400,-2400"); //temporary fix
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			fo.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("...Running in headless...");
			eo.addArguments("--headless");
			eo.addArguments("--window-position=-2400,-2400"); //temporary fix
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			eo.addArguments("--Inprivate");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
		}
		return eo;
	}


}
