package com.qa.opencart.constants;

import java.util.List;
/**
 * @author Veena Hegde
 */
public class AppConstants {
	
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
		
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final List<String> EXPECTED_ACC_PAGE_HEADERS_LIST = List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";	
	public static final String REGISTER_PAGE_TITLE = "Register Account";

	public static final String SHOPPING_CART_PAGE_TITLE = "Shopping Cart";
	public static final int PRODUCT_QTY = 2;
	
	public static final String CHECK_OUT_PAGE_TITLE = "Checkout";
	
	public static final String LOGIN_ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";
}
