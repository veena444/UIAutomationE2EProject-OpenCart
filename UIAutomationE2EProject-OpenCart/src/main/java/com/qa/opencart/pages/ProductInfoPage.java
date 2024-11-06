package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
/**
 * @author Veena Hegde
 */
public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By productImage = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMsg = By.cssSelector("div.alert.alert-success");
	private By shoppingCartLink = By.xpath("//div[contains(@class,'alert')]/a[2]");
	
	private Map<String,String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);		
	}
	
	public String getProductInfoPageHeader() {
		String productHeaderValue = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
//		System.out.println("Product Info Page Header ===> "+productHeaderValue);
		Log.info("Product Info Page Header ===> "+productHeaderValue);
		return productHeaderValue;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);		
		for(WebElement e: metaList) {
			String metaText = e.getText();
			String[] metaData = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", price);//since we don't have key, we are creating our own key
		productMap.put("exTaxPrice", exTaxPrice);
	}
	
	//combining both methods
	public Map<String, String> getProductData() {
//		productMap = new HashMap<String,String>();
		productMap = new LinkedHashMap<String,String>();
//		productMap = new TreeMap<String,String>();

		productMap.put("productHeader", getProductInfoPageHeader());
		getProductMetaData();
		getProductPriceData();
//		System.out.println("Product data: "+productMap);
		Log.info("Product data: "+productMap);
		return productMap;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
//		System.out.println("Images count ===> "+imagesCount);
		Log.info("Images count ===> "+imagesCount);
		return imagesCount;
	}
	
	public void updateQuantity() {
		eleUtil.getElement(quantity).clear();
		eleUtil.doSendKeys(quantity, String.valueOf(AppConstants.PRODUCT_QTY));
				
	}
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMessage = eleUtil.waitForElementVisible(successMsg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		String cartMsg = successMessage.substring(0, successMessage.length() - 1).replace("\n", "");
//		System.out.println(cartMsg);
		Log.info(cartMsg);
		return cartMsg;		

	}

	public ShoppingCartPage navigateToCartPage() {
		eleUtil.doClick(shoppingCartLink);
		return new ShoppingCartPage(driver);
	}

}
