package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("https://www.facebook.com/"); // test connect to slack
	}

	@Test
	public void TC_01_Nested() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println("Text of H5: " + driver.findElement(By.xpath("//h5[contains(text(),'Hello World!')]")).getText());
	}

	@Test
	public void TC_02_() {
//		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println("Text of H5 of TC_02: " + driver.findElement(By.xpath("//h5[@id='nested']")).getText());
	}

	@Test
	public void TC_03_() {
		// Login form displayed
		
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
