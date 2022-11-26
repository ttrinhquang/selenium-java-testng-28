package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_window_Tab {
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

//	@Test
	public void TC_01_windowTab() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(5);
		
		//Lấy ra ID của window hiện tại
		String parentPageWindowID = driver.getWindowHandle();
		
		//Click vào google button để hiện ra 1 tab mới.
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(5);
		
//		//Dùng SET để lấy hết tất cả ID ra. SET không lưu ID trùng, ID bị null
//		Set<String> allWindowIDs = driver.getWindowHandles();
//		
//		//Dùng vòng lặp để kiểm tra và chuyển qua Google Tab
//		for (String id : allWindowIDs) {
//			
//			if(!id.equals(parentPageWindowID)) {
//				
//				driver.switchTo().window(id);
//				sleepInSecond(5);		
//			}
//		}
		
		switchToWindowById(parentPageWindowID); //Từ vòng for ở trên ta biến thành fuction switchToWindowById
		
		driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys("Selenium");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");
		
		//Từ Google page switch ngược lại parent page
		String googleId = driver.getWindowHandle();
		
		switchToWindowById(googleId);
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
		
		}

//	@Test
	public void TC_02_switchByPageTitle() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(5);
		
		
		//Click vào google button để hiện ra 1 tab mới.
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(5);
		
		switchToWindowByPageTitle("Google");
		sleepInSecond(3);

		Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("automation");
		

		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
		
		//Click vào Facebook button để hiện ra 1 tab mới.
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(5);
		
		switchToWindowByPageTitle("Facebook");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation");
				
		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		
		//Click vào Tiki button để hiện ra 1 tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(5);
		
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"https://tiki.vn/");
		driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("Automation Testing");
		
	}

	@Test
	public void TC_03_techPanda() {
		
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		sleepInSecond(5);
		
		String parentID = driver.getWindowHandle();
		
		//Click vào Add to Compare of Iphone
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");
		
		//Click vào Add to Compare of Sony Xperia
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		//Click vào Compare button
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(3);
		
	
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
//		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
//		switchToWindowByPageTitle("Mobile");
		
		closeAllWindowExceptParent(parentID);
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
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
	
	public void switchToWindowById(String otherId) {
		//Dùng SET để lấy hết tất cả ID ra. SET không lưu ID trùng, ID bị null
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Dùng vòng lặp để kiểm tra và chuyển qua Google Tab
		for (String id : allWindowIDs) {
			
			if(!id.equals(otherId)) {
				
				driver.switchTo().window(id);
				sleepInSecond(5);		
			}
		}
	}
	
	public void switchToWindowByPageTitle(String pageTitle) {
		//Dùng SET để lấy hết tất cả ID ra. SET không lưu ID trùng, ID bị null
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Dùng vòng lặp để kiểm tra và chuyển qua Google Tab
		for (String id : allWindowIDs) {
			
				driver.switchTo().window(id);
				sleepInSecond(1);	
				
				//Lấy ra pageTitle của page
				String actualPageTitle = driver.getTitle();
				
				if(actualPageTitle.equals(pageTitle)) {
					break;
					
				}
			
		}
	}
	
	public void closeAllWindowExceptParent(String parentID)
	{
		//Dùng SET để lấy hết tất cả ID ra. SET không lưu ID trùng, ID bị null
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Dùng vòng lặp để kiểm tra và chuyển qua Google Tab
		for (String id : allWindowIDs) {
			
			if(!id.equals(parentID)) {
				
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(5);		
			}
		}
		driver.switchTo().window(parentID);

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
