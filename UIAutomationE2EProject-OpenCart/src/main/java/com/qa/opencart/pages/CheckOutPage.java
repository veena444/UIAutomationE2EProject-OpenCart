package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
/**
 * @author Veena Hegde
 */
public class CheckOutPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);	
	}
	
	
	public String getCheckOutPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.CHECK_OUT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
//		System.out.println("Checkout page title: "+title);
		Log.info("Checkout page title: "+title);
		return title;
	}

}
