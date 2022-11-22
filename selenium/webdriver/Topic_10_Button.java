package webdriver;

import org.openqa.selenium.support.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Verify_Enable_Disable() {
		
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(3);
		
//		chuyển qua tab login
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		
//		Verify login button is disabled
		
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
//		Enter email and password
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		
//		Verify login button is enable
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
//		Verify dang nhap button has red color
		String rgbacolor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		System.out.println("RGB Color" + rgbacolor);
		
//		Convert to hexa color
		String hexaColor = Color.fromString(rgbacolor).asHex().toUpperCase();
		System.out.println("RGB Color" + hexaColor);
		
//		Verify background color
		Assert.assertEquals(hexaColor, "#C92127");
		
		
		
		
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}
	
	@Test
	public void TC_04_() {
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
