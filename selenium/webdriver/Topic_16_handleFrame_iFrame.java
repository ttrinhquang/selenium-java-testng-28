package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_handleFrame_iFrame {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_kyna_iframe() {
		
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		
		//Verify facebook iframe hiển thị
		//Facebook iframe vẫn là một element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());

		//Cần phải switch vào đúng cái thẻ iframe chứa các element đó
//		driver.switchTo().frame(1); => dùng phương pháp index => không nên sử dụng vì các frames có thể thay đổi thứ tự
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		//Thao tác trên các element của facebook iframe.
		
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLike, "165K likes");
		
		//Cần switch về main page
		driver.switchTo().defaultContent();
		
		
		//Từ main page switch qua chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		
		//Click vào button in Chat iframe để hiển thị chat box
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Joe Biden");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("19001560");
		
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		
		driver.findElement(By.name("message")).sendKeys("alo automation testing");
		sleepInSecond(3);
		
		//Từ chat iframe quay về trang main
		driver.switchTo().defaultContent();
		
		//Search với từ khoá Excel
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		for (WebElement course : courseName) {
			
			
			Assert.assertTrue(course.getText().contains("Excel"));
			
		}
		
	}

	@Test
	public void TC_02_hdfc_frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//Switch qua frame chứa textbox
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("Putin");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		driver.findElement(By.cssSelector("input#fldPasswordDispId")).sendKeys("abc");
		sleepInSecond(5);
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
