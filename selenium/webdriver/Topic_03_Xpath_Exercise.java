package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		//Chorme
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("https://www.facebook.com/"); // test connect to slack
	}

	@Test
	public void TC_01_Run_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://vnexpress.net/");
		driver.quit();

	}

	@Test
	public void TC_02_Run_Firefox() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("https://vnexpress.net/");
		driver.quit();

		
	}


	@Test
	public void TC_03_Incorrect_Email() {
		// Login form displayed
		
	}
	
	@Test
	public void TC_04_Invalid_Password() {
		// Login form displayed
		
	}
	@Test
	public void TC_05_Incorrect_Password() {
		// Login form displayed
		
	}
	@Test
	public void TC_06_Incorrect_Phone() {

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
