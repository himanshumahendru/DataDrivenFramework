package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class OpenAccount extends BaseTest {
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void openAcc(String customer, String currency) throws InterruptedException {
		click("openAcc_CSS");
		Thread.sleep(5000);
		select("customer_CSS",customer);
		select("currency_CSS",currency);
		click("processBtn_CSS");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Account created successfully"),"Customer was not added successfully");
		Assert.fail("Open Account Failed Intentionally");
		
		
		
		
	}

}
