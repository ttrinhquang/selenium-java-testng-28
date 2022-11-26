package webdriver;

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

public class Topic_15_fixedPopup {
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
	public void TC_01_fixedPopup() {
		
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(5);
		
		By loginPopup = By.xpath("(//div[@id='modal-login-v1'])[1]");
		
		
		//Mới vào page thì check popup này xuất hiện hay chưa
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		//Click để hiển thị popup
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		
		//verify popup hiện thị
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		//Nhập thông tin không hợp lệ
		
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]  input#account-input")).sendKeys("automationtesting");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]  input#password-input")).sendKeys("automationtesting");
		
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]  button.btn-login-v1")).click();
		sleepInSecond(3);
		
		//Verify error message hiển thị
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]  div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		

	}

//	@Test
	public void TC_02_fixedPopup_tiki () {
		
		driver.get("https://tiki.vn/");
		sleepInSecond(5);
		
		//Do popup ko có trong HTML Element nên verify bằng size() với findElementS
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(), 0);
		
		//click on đăng nhập
		
		driver.findElement(By.cssSelector("span.Userstyle__ItemText-sc-6e6am-3")).click();
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		
		//Verify 2 errors message is displayed
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		
		//Close pop up
		driver.findElement(By.cssSelector("img.close-img")).click();
		Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(), 0);
	}

	@Test
	public void TC_03_fixedPopup_facebook() {
		driver.get("https://www.facebook.com/");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		
		//verify pop up hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
		
		//Close popup 
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
		sleepInSecond(3);
		
		//Verify popup không hiển thị
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);
		
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
//		driver.quit();
	}
}
