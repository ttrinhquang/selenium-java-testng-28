package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_uploadFile {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;

	
	String beachFileName = "beach.jpg";
	String computerFileName = "computer.jpg";
	String mountainFileName = "mountain.jpg";
	
	String beachFilePath = projectPath + "\\uploadFiles\\" + beachFileName;
	String computerFilePath = projectPath + "\\uploadFiles\\" + computerFileName;
	String mountainFilePath = projectPath + "\\uploadFiles\\" + mountainFileName;

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
	public void TC_01_uploadOneFilePerTime() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		
		//Load file lên trang web
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(computerFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(mountainFilePath);
		sleepInSecond(1);
		
		//Verify file đã được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountainFileName + "']")).isDisplayed());
		
		
		//Click Upload
		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table  button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(5);
		}
		
		//Verify upload file thành công bằng Link 
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + mountainFileName + "']")).isDisplayed());
		
		//Verify upload file thành công bằng Image (using JavascriptExecutor)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + mountainFileName + "')]"));


	}

	@Test
	public void TC_02_loadMultipleFileOneTime() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		
		//Load nhiều files lên trang web cùng một lúc
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + mountainFilePath );
		
		sleepInSecond(1);
		
		//Verify file đã được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountainFileName + "']")).isDisplayed());
		
		
		//Click Upload
		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table  button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(5);
		}
		
		//Verify upload file thành công bằng Link 
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + mountainFileName + "']")).isDisplayed());
		
		//Verify upload file thành công bằng Image (using JavascriptExecutor)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + mountainFileName + "')]"));

		
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
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
