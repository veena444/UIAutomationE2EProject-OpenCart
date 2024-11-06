package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
/**
 * @author Veena Hegde
 */
public class ShoppingCartPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider

	public Object[][] getAddToCartData() {
		return new Object[][] { { "macbook", "MacBook Pro" } };
	}

	@Test(dataProvider = "getAddToCartData")
	public void getAddToCartTest(String searchKey, String productName) {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		productInfoPage.updateQuantity();
		String successMessage = productInfoPage.addProductToCart();
//		System.out.println(successMessage);
		Log.info(successMessage);
		Assert.assertEquals(successMessage, "Success: You have added " + productName + " to your shopping cart!");

		shoppingCartPage = productInfoPage.navigateToCartPage();

	}

	@Test
	public void shoppingCartPageTitleTest() {
		String accTitle = shoppingCartPage.getShoppingCartPageTitle();
		Assert.assertEquals(accTitle, AppConstants.SHOPPING_CART_PAGE_TITLE);
	}

}
