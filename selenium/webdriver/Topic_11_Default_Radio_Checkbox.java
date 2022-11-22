package webdriver;

import java.util.Iterator;
import java.util.List;
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

public class Topic_11_Default_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Random rand;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

//	@Test
	public void TC_01_jotForm() {

		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Choose checkbox: Anemia - Fainting Spells
//		
//		driver.findElement(By.xpath("//input[@value='Anemia']")).click();
//		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		checkToCheckboxOrRadio("//input[@value='Anemia']");
		checkToCheckboxOrRadio("//input[@value='Fainting Spells']");
		
		sleepInSecond(1);
		
		
//		scrollToElement("li#cid_79");
		//Verify they have chosen
		
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Anemia']")).isSelected());
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());

		Assert.assertTrue(isElementSelected("//input[@value='Anemia']"));
		Assert.assertTrue(isElementSelected("//input[@value='Fainting Spells']"));
		//Choose Radio: 5+ days - 1-2 cups/day
//		
//		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
//		driver.findElement(By.xpath("//input[@value='1-2 cups/day']")).click();

		
		checkToCheckboxOrRadio("//input[@value='5+ days']");
		checkToCheckboxOrRadio("//input[@value='1-2 cups/day']");
//		
		//Verify Radio elements have chosen
		
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1-2 cups/day']")).isSelected());
		
		Assert.assertTrue(isElementSelected("//input[@value='5+ days']"));
		Assert.assertTrue(isElementSelected("//input[@value='1-2 cups/day']"));
		
		
		//Uncheck Anemia - Fainting Spells 
		
//		driver.findElement(By.xpath("//input[@value='Anemia']")).click();
//		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		
		unCheckToCheckboxOrRadio("//input[@value='Anemia']");
		unCheckToCheckboxOrRadio("//input[@value='Fainting Spells']");
		
		//Verify Uncheck successfully
//		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Anemia']")).isSelected());
//		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());
		
		Assert.assertFalse(isElementSelected("//input[@value='Anemia']"));
		Assert.assertFalse(isElementSelected("//input[@value='Fainting Spells']"));
		
		//Uncheck Radio 5+ days 1-2 cups/day
		
//		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
//		driver.findElement(By.xpath("//input[@value='1-2 cups/day']")).click();
		checkToCheckboxOrRadio("//input[@value='Never']");
		checkToCheckboxOrRadio("//input[@id='input_77_0']");
		
		
		
		
		//Verify uncheck successfully
		Assert.assertFalse(isElementSelected("//input[@value='5+ days']"));
		Assert.assertFalse(isElementSelected("//input[@value='1-2 cups/day']"));
		
		
	}

//	@Test
	public void TC_02_jotForm_checkAll() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		//Dung vong lap for de duyet qua va click chon
		
		for (WebElement checkbox : allCheckBoxes) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			
		}	
		// Dung vong lap for de duyet qua va kiem tra
		for (WebElement checkbox : allCheckBoxes) {
			Assert.assertTrue(checkbox.isSelected());
			}
		
		// Dung vong lap for de duyet qua va bo chon tat ca
		
		for (WebElement checkbox : allCheckBoxes) {
			unCheckToCheckboxOrRadio(checkbox);
			sleepInSecond(1);
		}
			
	
	
	// Dung vong lap for de duyet qua va kiem tra
		for (WebElement checkbox : allCheckBoxes) {
		Assert.assertFalse(checkbox.isSelected());
		}
	}

	@Test
	public void TC_03_defaulCheckbox_radioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(6);
		
		checkToCheckboxOrRadio("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		Assert.assertTrue(isElementSelected("//label[text()='Luggage compartment cover']/preceding-sibling::input"));
		
		unCheckToCheckboxOrRadio("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		Assert.assertFalse(isElementSelected("//label[text()='Luggage compartment cover']/preceding-sibling::input"));
		
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
	
	
	public void checkToCheckboxOrRadio(String xpathLocator) {
		// Kiem tra truoc no da chon hay chua
		
		// Neu chon roi thi ko can click nua
		
		//New chua chon thi click vao -> duoc chon
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			
			driver.findElement(By.xpath(xpathLocator)).click();
			
		}
	}
	
	public void unCheckToCheckboxOrRadio(String xpathLocator) {

		if (driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			
			driver.findElement(By.xpath(xpathLocator)).click();
			
		}
	}
	
	public void unCheckToCheckboxOrRadio(WebElement element) {

		if (element.isSelected()) {
			
			element.click();
			
		}
	}
	
	public boolean isElementSelected(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator)).isSelected();
	}
	

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
