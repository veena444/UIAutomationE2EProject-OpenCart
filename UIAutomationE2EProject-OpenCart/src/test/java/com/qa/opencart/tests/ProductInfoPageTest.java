package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.logger.Log;
/**
 * @author Veena Hegde
 */
public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
		};
	}
	
	@Test(dataProvider="getProductData")
	public void productHeaderTest(String searchKey, String productName) {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductInfoPageHeader(), productName);
	}
	
	
	@DataProvider
	public Object[][] getProductInfoTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro","Apple","Product 18","800","In Stock","$2,000.00"},	
		};
		
	}
	@Test(dataProvider="getProductInfoTestData")
	public void productInfoTest(String searchKey,String productName, String brand, String prodCode,String rewardPoints,String availability,String prodPrice)
			 {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Map<String,String> actProductDataMap = productInfoPage.getProductData();
		
		//single functionality having multiple validations we should use soft assertion
		softAssert.assertEquals(actProductDataMap.get("Brand"), brand);
		softAssert.assertEquals(actProductDataMap.get("Product Code"), prodCode);
		softAssert.assertEquals(actProductDataMap.get("Reward Points"), rewardPoints);
		softAssert.assertEquals(actProductDataMap.get("Availability"), availability);
		softAssert.assertEquals(actProductDataMap.get("productPrice"), prodPrice);
		softAssert.assertEquals(actProductDataMap.get("exTaxPrice"), prodPrice);
		softAssert.assertAll();
		
	}
	
	@DataProvider
	public Object[][]  getProductImagesCountData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3},
		};
	}
	
	@Test(dataProvider="getProductImagesCountData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
	}
	
	
    @DataProvider	
	public Object[][] getAddToCartData(){
		return new Object[][]{
		{"macbook","MacBook Pro"}
		};
	}
	
	@Test(dataProvider = "getAddToCartData")
	public void getAddToCartTest(String searchKey,String productName) {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		productInfoPage.updateQuantity();
		String successMessage = productInfoPage.addProductToCart();
//		System.out.println(successMessage);
		Log.info(successMessage);
		Assert.assertEquals(successMessage, "Success: You have added " + productName +" to your shopping cart!");
		
		shoppingCartPage = productInfoPage.navigateToCartPage();
			
	}

}
