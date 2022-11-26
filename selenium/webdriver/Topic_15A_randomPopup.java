package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15A_randomPopup {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

//	@Test
	public void TC_01_javaCodeGeeks_inHTML_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(10);
		
		//yêu cầu
		//1 - Nếu pop có xuất hiện thì click để subscribe
		//2 - Neu popup khong có xuất hiện thì qua step tiếp theo
		
		WebElement popup = driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])"));
		
		if (popup.isDisplayed()) {
			
			//Nhap email
			
			driver.findElement(By.cssSelector("input[name='lepopup-20']")).sendKeys(getRandomEmailAddress());
			sleepInSecond(3);
			
			driver.findElement(By.cssSelector("a.lepopup-button")).click();
			sleepInSecond(10);
			
		} 
		
		//Verify popup is not displayed
		Assert.assertFalse(popup.isDisplayed());
		
		//Sau khi popup da duoc dong, thì search Agile Testing Explained key
		
		driver.findElement(By.cssSelector("input#s")).sendKeys("Agile Testing Explained");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='page-title']/span[text()='Agile Testing Explained']")).isDisplayed());
		
		
	}

//	@Test
	public void TC_02_KMplayerPopup_inHTML_DOM() {
		
		driver.get("https://kmplayer.com/home");
		sleepInSecond(15);
		
		//Nếu popup xuất hiện thì close nó đi
		//Nếu không xuất hiện thì go to next step
		
		WebElement popup = driver.findElement(By.cssSelector("img#support-home"));
		
		if (popup.isDisplayed()) {
			
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			
		}
		
		driver.findElement(By.xpath("//li//a[text()='PC 64X']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='KMPlayer 64X']")).isDisplayed());
		
		
	}

	@Test
	public void TC_03_deHieu_Not_In_HTML_DOM() {
		
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		List<WebElement> popups = driver.findElements(By.cssSelector("div.popup-content"));
		
		if(popups.size() > 0 && popups.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Joe Biden");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(getRandomEmailAddress());
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("19001560");
			sleepInSecond(5);
			driver.findElement(By.cssSelector("button#close-popup")).click();
		}
		
		//Go to next step
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("Khóa học Thiết kế tủ điện");
		driver.findElement(By.cssSelector("i.fa-search")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h4.name-course")).getText(), "Khóa học Thiết kế tủ điện");
		
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

	public String getRandomEmailAddress() {
		Random rand = new Random();
		return "john" + rand.nextInt(9999) + "@gmail.com";
	}	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
