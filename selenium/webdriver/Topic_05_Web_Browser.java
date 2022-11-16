package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();


	}

	@Test
	public void TC_01_Verify_Url()  {
		
		driver.get("http://live.techpanda.org/"); 
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		
	}

	@Test
	public void TC_02_Verify_Title() {

		driver.get("http://live.techpanda.org/"); 
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		sleepInSecond(3);
		
	}

	@Test
	public void TC_03_Navigate_function_back_forward() {

		driver.get("http://live.techpanda.org/"); 
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Get_Page_Source_Code() {
		driver.get("http://live.techpanda.org/"); 
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
		
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
