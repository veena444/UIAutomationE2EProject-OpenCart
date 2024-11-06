package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
/**
 * @author Veena Hegde
 */
public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.cssSelector("input.btn.btn-primary");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img.img-responsive");
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	private By registerLink = By.linkText("Register");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil= new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
//		System.out.println("Login page title: "+title);
		Log.info("Login page title: "+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page url: "+url);
		Log.info("Login page url: "+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	public boolean isLogoExist() {
		return eleUtil.isElementDisplayed(logo);
	}
	
	public AccountsPage doLogin(String userName, String pwd) {
			eleUtil.waitForElementVisible(username, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(userName);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginBtn);			
			return new AccountsPage(driver);		
		}
	
	public boolean doInvalidLogin(String userName, String pwd) {
		WebElement usernameElement = eleUtil.waitForElementVisible(username, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		eleUtil.doSendKeys(usernameElement, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMsg = eleUtil.waitForElementVisible(loginErrorMessage, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		Log.info("Login error ===> "+ errorMsg);
		if(errorMsg.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
