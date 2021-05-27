package extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Basic Selenium Report");
		htmlReporter.config().setReportName("First Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester", "Himanshu Mahendru");
		extent.setSystemInfo("Build Number", "12345");
		extent.setSystemInfo("Organisation", "Mahendru Automation");
		
		return extent;
	}

}
