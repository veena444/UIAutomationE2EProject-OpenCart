package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
/**
 * @author Veena Hegde
 */
public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] invalidLoginData() {
		return CSVUtil.csvData("login");
	}
	
	@Test(dataProvider="invalidLoginData")	
	public void invalidLoginTest(String userName, String password) {
		Assert.assertTrue(loginPage.doInvalidLogin(userName, password));
	}

}
