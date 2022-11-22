package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_customCheckbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Random rand;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_customCheckbox() {
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		sleepInSecond(8);
		
//		driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
		
//		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());

//		driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div")).click();
//		sleepInSecond(3);
		
//		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div")).isSelected());
		By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	}

//	@Test
	public void TC_02_customRadio() {
		
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(8);
		
		By customRadio = By.xpath("//label[contains(text(),' Spring ')]/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(customRadio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(customRadio).isSelected());
		
	}

//	@Test
	public void TC_03_customCheckVNDirect() {
		
		driver.get("https://account-v2.vndirect.com.vn/");
		sleepInSecond(5);
		
		By termCheckbox = By.xpath("//input[@name='acceptTerms']");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_customRadio_googlePage() {
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canthoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
		
		
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "false");
		
//		Dung isDisplayed to check
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		checkToCheckbox("//div[@aria-label='Cần Thơ']");
		
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		
		By miQuangCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");
		
		checkToCheckbox("//div[@aria-label='Mì Quảng']");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(miQuangCheckbox).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Mì Quảng' and @aria-checked='true']")).isDisplayed());
		
		unCheckToCheckbox("//div[@aria-label='Mì Quảng']");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(miQuangCheckbox).getAttribute("aria-checked"), "false");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Mì Quảng' and @aria-checked='false']")).isDisplayed());
		
	}
	
	public void checkToCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if(element.getAttribute("aria-checked").equals("false")) {
			element.click();
			
		}
		
	}
	
	public void unCheckToCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if(element.getAttribute("aria-checked").equals("true")) {
			element.click();
			
		}
		
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
