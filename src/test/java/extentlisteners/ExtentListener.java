package extentlisteners;

import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.ScreenshotUtil;

public class ExtentListener implements ITestListener {

	static Date d = new Date();
	static String fileName = d.toString().replace(" ", "_").replace(":", "_")+".html";
    private static ExtentReports extent = ExtentManager.createInstance("./target\\reports\\" + fileName);
    public  static ExtentTest test;
    
	
	
	
	public void onTestStart(ITestResult result) {
	 test = extent.createTest(result.getTestClass().getName()+ "   @Test Name -  " + result.getMethod().getMethodName());
		
	}

	
	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>Test Case Passed - "+methodName+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
	}

	
	public void onTestFailure(ITestResult result) {
		//capture SS 
		ScreenshotUtil.captureSS();
		
		// ReportNG Handling 
		System.setProperty("org.uncommons.reportng.escape-output","false");
		Reporter.log("<a href ="+ScreenshotUtil.fileName+" target=\"_blank\">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a href="+ScreenshotUtil.fileName+" target=\"_blank\"><img src="+ScreenshotUtil.fileName+" height=200 width=200></a>");
		
		/////////////////
		String exception = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(exception);
		String methodName = result.getMethod().getMethodName();
		String logText = "<b> Test Case Failed - "+methodName+ "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.fail(m);
		
		String SS = ScreenshotUtil.fileName;
		test.fail("<b><font color = red>SS of the failed testcase</font> </b><br>",MediaEntityBuilder.createScreenCaptureFromPath(SS).build());
		
	}

	
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>Test Case Skipped - "+methodName+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ITestContext context) {

		if(extent != null) {
			extent.flush();
		}
	}

}
