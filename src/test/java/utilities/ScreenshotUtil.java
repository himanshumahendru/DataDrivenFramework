package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ScreenshotUtil extends BaseTest {

	public static String fileName;
	
public static void captureSS() {
		
		Date d = new Date();
		fileName = d.toString().replace(":","_").replace(" ", "_")+".jpg";
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// Extent Report Handling through mvn
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\target\\reports\\" +fileName));
			// ReportNG Handling through mvn
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" +fileName));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

public static void captureElementSS(WebElement element) {
	
	Date d = new Date();
	fileName = d.toString().replace(":","_").replace(" ", "_")+".jpg";
	
	File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\Screenshot\\" +fileName));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
