package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;
																					
public class BankManagerLoginTest extends BaseTest {
	
    @Test
	public void bankManagerLogin() {
    	click("bmlBtn_CSS");
    	Assert.assertTrue(isElementPresent("AddCustomerBtn_CSS"), "Bank Manager Login Failed");
    	
    	Assert.fail("Bank Manager Login Failed Intentionally");
    	
		
		
	}
	
}
