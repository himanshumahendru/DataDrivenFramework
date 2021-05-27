package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class AddCustomer extends BaseTest{
	
	@Test(dataProviderClass = DataUtil.class , dataProvider = "dp")
	public void addCust(String firstName, String lastName, String postCode) {
		click("AddCustomerBtn_CSS");
		type("firstName_CSS",firstName);
		type("lastName_CSS",lastName);
		type("postCode_CSS",postCode);
		click("AddCustConfirm_CSS");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Customer added successfully"), "Customer was not added successfully");
		alert.accept();
		
		Assert.fail("Add Customer Failed Intentionally");
		
		
	}

}
