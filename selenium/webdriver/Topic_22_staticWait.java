package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_staticWait {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_notEnoughTime() {
		

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Static Wait
		sleepInSecond(3);
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
		
		
	}

	@Test
	public void TC_02_enoughTime() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Static Wait
		sleepInSecond(5);
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}

	@Test
	public void TC_03_moreTime() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Static Wait
		sleepInSecond(10);
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
		
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
//		driver.quit();
	}
}
