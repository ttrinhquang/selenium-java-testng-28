package webdriver;

import static org.testng.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.id("disable_password");
	By biographyTextarea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#java");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();


	}

//	@Test
	public void TC_01_isDisplayed()  {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium Web elements");
			System.out.println("Email element is displayed");	
		}else {
			System.out.println("Email element is not displayed");
			
		}
		
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium GRID");
			System.out.println("EducationTextArea is displayed");
		}
		else {
			System.out.println("EducationTextArea is not displayed");
			
		}
		
		
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 is displayed");
		}
		else {
			System.out.println("Age under 18 is not displayed");
			
		}
		
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("User5 is displayed");
		}
		else {
			System.out.println("User5 is not displayed");
			
		}
		
		
		
	}

//	@Test
	public void TC_02_isEnabled() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password textbox is enabled");	
		}else {
			System.out.println("Password textbox is disable");
			
		}
		
		if(driver.findElement(biographyTextarea).isEnabled()) {
			System.out.println("Biography textarea is enabled");	
		}else {
			System.out.println("Biography textarea is disable");
	
		}
		
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email textbox is enabled");	
		}else {
			System.out.println("Email textbox is disable");
	
		}
	}

//	@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
		
	}
	
	@Test
	public void TC_04_MailChimp() {
		
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		
		driver.findElement(passwordTextbox).sendKeys("abc");
//		driver.findElement(signupButton).click();
		
		sleepInSecond (3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		
		driver.findElement(passwordTextbox).sendKeys("ABC");
//		driver.findElement(signupButton).click();
		
		sleepInSecond (3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		
		driver.findElement(passwordTextbox).sendKeys("123");
//		driver.findElement(signupButton).click();
		
		sleepInSecond (3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		
		driver.findElement(passwordTextbox).sendKeys("@!#");
//		driver.findElement(signupButton).click();
		
		sleepInSecond (3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		
		driver.findElement(passwordTextbox).sendKeys("ABCXYZGHM");
//		driver.findElement(signupButton).click();
		
		sleepInSecond (3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		
		driver.findElement(passwordTextbox).sendKeys("123abcABC@#$");
//		driver.findElement(signupButton).click();
		
		sleepInSecond (3);
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
		
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
