package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
/**
 * @author Veena Hegde
 */
public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By passwordConfirm = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit']");
	
	private By successMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}

	public String getRegisterPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.REGISTER_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
//		System.out.println("Register page title: "+title);
		Log.info("Register page title: "+title);
		return title;
	}
	
	public boolean userRegistration(String firstName,String lastName,String email, String telephone,String password,String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.passwordConfirm, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueBtn);
		
		
		String successMsg = eleUtil.waitForElementVisible(successMessage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
//		System.out.println(successMsg);
		Log.info(successMsg);
		
		if(successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else {
			return false;
		}

		
	}

}
