package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_handleAlert {
	WebDriver driver;
	Alert alert;
	String homePageUrl;
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
	public void TC_01_acceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		//Switch qua Alert truoc khi handle nó
		alert = driver.switchTo().alert();
		
		//Verify alert title truoc khi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//Acept 1 alert
		alert.accept();
		
		//Verify accept Alert thanh cong
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
		
		
		
	}

	//@Test
	public void TC_02_confirmAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		//Switch qua Alert truoc khi handle nó
		alert = driver.switchTo().alert();
		
		//Verify alert title truoc khi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Cancel 1 alert
		alert.dismiss();
		
		//Verify accept Alert thanh cong
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
		
		
	}

//	@Test
	public void TC_03_promptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		String keyword = "Selenium WebDriver";
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		//Switch qua Alert truoc khi handle nó
		alert = driver.switchTo().alert();
		
		//Verify alert title truoc khi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//Sendkey to a prompt
		alert.sendKeys(keyword);
		sleepInSecond(10);
		
		//Acept 1 alert
		alert.accept();
		
		//Verify accept Alert thanh cong
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + keyword);
		
	}
	
//	@Test
	public void TC_04_accept_Alert_Login() {
		
		driver.get("https://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		//Accept alert
		
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
		
		
	}
	
@Test
	public void TC_05_authenticationAlert_way_1() {
		
		//Cách 1: Nhập user/password vào URL trước khi nó open
		//Example: http://username:password@the-internet.herokuapp.com/basic_auth
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must"));
				}
	
	@Test
	public void TC_06_authenticationAlert_way_2() {
		
		//Cách 1: Nhập user/password vào URL trước khi nó open
		//Example: http://username:password@the-internet.herokuapp.com/basic_auth
		
		driver.get("http://the-internet.herokuapp.com/");
		sleepInSecond(5);
		
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		System.out.println(basicAuthenUrl);
		
		System.out.println(getAuthenticationUrl(basicAuthenUrl, "admin", "admin"));
		driver.get(getAuthenticationUrl(basicAuthenUrl, "admin", "admin"));
		
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must"));
				}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAuthenticationUrl(String basicAuthenUrl, String userName, String password) {
		String[] authenUrlArray = basicAuthenUrl.split("//");
		basicAuthenUrl = authenUrlArray[0] + "//" + userName + ":" + password + "@" + authenUrlArray[1];
		return basicAuthenUrl;
	}
	
	

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
