package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
/**
 * @author Veena Hegde
 */
public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By checkOutBtn = By.xpath("//div[@class='pull-right']/a");

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);	
	}
	
	public String getShoppingCartPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.SHOPPING_CART_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
//		System.out.println("Shopping cart page title: "+title);
		Log.info("Shopping cart page title: "+title);
		return title;
	}
		
	public CheckOutPage navigateToCheckoutPage() {
		eleUtil.doClick(checkOutBtn);
		return new CheckOutPage(driver);
	}
	

}
