package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Conditions_Element_Status {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 10);

	}

	@Test
	public void TC_01_visible_displayed_visibility() {
		
		driver.get("https://www.facebook.com/");
		
		//Có trên UI (Must)
		//Có trong HTML/DOM (Must)
		
		//Wait email address textbox hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		
	}

	@Test
	public void TC_02_invisible_undisplayed_invisibility_case_01() {
		//không có trên UI (must)
		//Có trong HTML
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Wait Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_03_invisible_undisplayed_invisibility_case_02() {
		//Không có trên UI (must)
		//Không có trong HTML/DOM
		
		driver.get("https://www.facebook.com/");
		
		//Wait Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	@Test
	public void TC_04_Presence_case_01() {
		//Có displayed trên UI
		//Có trong cây HTML/DOM (must)
		
		driver.get("https://www.facebook.com/");
		
		//Có trên UI (Must)
		//Có trong HTML/DOM (Must)
		
		//Wait email address textbox hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
	}
	
	@Test
	public void TC_05_Presence_Case_02() {
		//Không có displayed trên UI
		//Có ở trong cây HTML/DOM
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));

		
	}
	@Test
	public void TC_06_staleness() {
		//Không có displayed trên UI (must)
		//Không có trong HTML
		
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Phần 01: Element có trong cây HTML
		
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		
		//Thao tác với element khác để làm cho element reEnterEmail không còn xuất hiện trong DOM
		
		//close pop up
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		//Chờ cho re-enter Email textbox không còn trong dom
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
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
