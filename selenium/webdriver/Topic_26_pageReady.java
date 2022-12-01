package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_pageReady {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		action = new Actions(driver);
		
//		jsExecutor = (JavascriptExecutor) driver;
//		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 60);


	}

//	@Test
	public void TC_01_pageReady_orangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		String employeeName = "Alice.Duval";
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		//Dùng hàm pageread để page mới load xong
		action.moveToElement(driver.findElement(By.cssSelector("input.oxd-input--active"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Time at Work']")).getText(), "Time at Work");
		
		//Click xong chuyển qua trang mới
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input")).sendKeys(employeeName);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Load lại 1 phần trang
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + employeeName + "']")).isDisplayed());
		

		
	}

	@Test
	public void TC_02_pageReady_testBlog() {
		driver.get("https://blog.testproject.io/");
		String keyword = "Selenium";

		
		//Hover chuột vào một vị trí nào đó trong page để page có thể load hoàn toàn 100%
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click(); 
		
		Assert.assertTrue(driver.findElement(By.cssSelector("h2.page-title>span")).isDisplayed());
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement title : postTitles) {
			String postTitleText = title.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(postTitleText.contains(keyword));
		}
		
		
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


	public boolean isPageLoadedSuccess() {

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		}
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
