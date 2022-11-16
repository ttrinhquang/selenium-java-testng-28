package webdriver;

import static org.testng.Assert.assertFalse;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_part02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	Random rand;
	String emailAddress, firstName, lastName, password, fullName;
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.id("disable_password");
	By biographyTextarea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#java");
	
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		rand = new Random();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		password = "12345678";

	

	}

//	@Test
	public void TC_01_empty_Email_Password()  {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond (3);
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond (3);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
		
	}

//@Test
	public void TC_02_invalid_Email() {

	driver.get("http://live.techpanda.org/");
	
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond (3);
	
	driver.findElement(By.id("email")).sendKeys("12345123412342@12342342342342342342");
	
	driver.findElement(By.id("pass")).sendKeys("123456");
	
	driver.findElement(By.id("send2")).click();
	sleepInSecond (3);
	
	Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	
	}

//@Test
	public void TC_03_password_less_than_6() {
	driver.get("http://live.techpanda.org/");
	
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond (3);
	
	driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	
	driver.findElement(By.id("pass")).sendKeys("123");
	
	driver.findElement(By.id("send2")).click();
	sleepInSecond (3);
	
	Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	
		
	}
	
//@Test
	public void TC_04_incorrect_email_password() {
		
	driver.get("http://live.techpanda.org/");
	
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond (3);
	
	driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	
	driver.findElement(By.id("pass")).sendKeys("123123123");
	
	driver.findElement(By.id("send2")).click();
	sleepInSecond (3);
	
//	Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(), "Invalid login or password.");

		
	}


@Test
	public void TC_05_create_new_account() {
		
	driver.get("http://live.techpanda.org/");
	
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond (3);
	
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	sleepInSecond (3);
	
	driver.findElement(By.id("firstname")).sendKeys(firstName);
	driver.findElement(By.id("lastname")).sendKeys(lastName);
	driver.findElement(By.id("email_address")).sendKeys(emailAddress);
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("confirmation")).sendKeys(password);
	
	driver.findElement(By.xpath("//button[@title='Register']")).click();
	
	sleepInSecond (3);
	
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
	
	String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	System.out.println(contactInformationText);
	
	Assert.assertTrue(contactInformationText.contains(fullName));
	Assert.assertTrue(contactInformationText.contains(emailAddress));
	
	
	driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
	sleepInSecond (1);
	driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	sleepInSecond (5);
	Assert.assertTrue( driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		
}

@Test
	public void TC_06_login_with_valid_email_password() {
	
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	sleepInSecond (3);
	driver.findElement(By.id("email")).sendKeys(emailAddress);
	driver.findElement(By.id("pass")).sendKeys(password);
	driver.findElement(By.id("send2")).click();
	
	sleepInSecond (3);
	
	String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	System.out.println(contactInformationText);
	
	Assert.assertTrue(contactInformationText.contains(fullName));
	Assert.assertTrue(contactInformationText.contains(emailAddress));
	
	
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
