package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
/**
 * @author Veena Hegde
 */
public class RegisterPageTest extends BaseTest{
	
@BeforeClass
	
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();		
	}

	//to generate random email ID
	public String getRandomEmail() {
		return "uiautomation" +System.currentTimeMillis()+"@open.com";
	}
	
	@Test
	public void registerPageTitleTest() {
		String actTitle = registerPage.getRegisterPageTitle();
		Assert.assertEquals(actTitle, AppConstants.REGISTER_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getCreateData() {
		return new Object[][] {
			{"Virat","Kohli",getRandomEmail(),"1234567890","kohli@123","yes"},
			{"David","Beckham",getRandomEmail(),"7894561230","david@123","yes"},
			{"Michael","Phelps",getRandomEmail(),"4563217890","phelps@123","yes"},
			{"Serena","Williams",getRandomEmail(),"3698521470","serena@123","yes"},
			{"Coco","Gauff",getRandomEmail(),"5467590231","coco@123","yes"},
			
		};
	}
	
	@Test(dataProvider="getCreateData")
	public void userRegisterTest(String firstName,String lastName,String Email,String telephone,String password,String subscribe) {
		Assert.assertTrue(registerPage.userRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
		
	}

}
