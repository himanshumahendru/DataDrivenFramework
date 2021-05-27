package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;

public class BaseTest {

	public static WebDriver driver;
	public static Logger log = Logger.getLogger(BaseTest.class.getName());
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\exceldata.xlsx");
	public static WebDriverWait wait;
	public static WebElement dropdown;

	public static void type(String key, String value) {
		if(key.endsWith("_XPATH")) 
			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
		else if(key.endsWith("_ID"))
			driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
		else if(key.endsWith("_CSS"))
			driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
		
		log.info(key + "  Element has been Typed  " + value);
		ExtentListener.test.log(Status.INFO, key + "  Element has been Typed  " + value);
	}
	
	
	public static void click(String key) {
		if(key.endsWith("_XPATH"))
			driver.findElement(By.xpath(OR.getProperty(key))).click();
		else if(key.endsWith("_CSS"))
			driver.findElement(By.cssSelector(OR.getProperty(key))).click();
		else if(key.endsWith("_ID"))
			driver.findElement(By.id(OR.getProperty(key))).click();
		log.info(key +  "  has been clicked");
		ExtentListener.test.log(Status.INFO, key +  "  has been clicked");
	}
	
	public static void select(String key, String value) {
		
		if(key.endsWith("_XPATH")) {
			
			dropdown = driver.findElement(By.xpath(OR.getProperty(key)));
		    ExtentListener.test.info("Drop Down Element is "+ dropdown);
		}
		else if(key.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));
			ExtentListener.test.info("Drop Down Element is "+ dropdown);
		}
		else if(key.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(key)));
			ExtentListener.test.info("Drop Down Element is "+ dropdown);	
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info(key +  "  has been selected from Dropdown with value  "+ value);
		ExtentListener.test.log(Status.INFO, key +  "  has been selected from Dropdown with value  "+ value);
	}
	
	public static boolean isElementPresent(String key) {
		try {
			if(key.endsWith("_XPATH"))
			driver.findElement(By.xpath(OR.getProperty(key)));
		else if(key.endsWith("_CSS"))
			driver.findElement(By.cssSelector(OR.getProperty(key)));
		else if(key.endsWith("_ID"))
			driver.findElement(By.id(OR.getProperty(key)));
	
			log.info(key + "  Element is present");
			ExtentListener.test.log(Status.INFO, key + "  Element is present");
			return true;
		}catch(Throwable t) {
		log.error(key + " Element not found" + " getting exception as- " + t.getMessage());
		ExtentListener.test.log(Status.FAIL,key + " Element not found" + " getting exception as- " + t.getMessage());
		return false;
		
		}
		
	}
	@BeforeSuite
	public void setup() {

		if (driver == null) {

			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");
			log.info("Logger configured!!!");

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR properties configured!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.info("Config properties configured!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (Config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
					
			driver.get(Config.getProperty("testsiteURL"));
			log.info("Navigated to TestSite URL" + Config.getProperty("testsite") );
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			
			wait = new WebDriverWait(driver,Integer.parseInt(Config.getProperty("explicit.wait")));

			// getting up DB connection
			//DbManager.setMysqlDbConnection();
			
		}

	}

	@AfterSuite
	public void teardown() {
		
		driver.quit();
		log.info("Test Case Execution completed");

	}

}
