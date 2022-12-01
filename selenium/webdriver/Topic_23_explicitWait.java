package webdriver;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_explicitWait {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	

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
		driver.manage().window().maximize();
	
	}

//	@Test
	public void TC_01_notEnoughTime() {
		

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//ExplicitWait
		explicitWait = new WebDriverWait(driver, 3);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
		
		
	}
	
//	@Test
	public void TC_02_moreTime() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//ExplicitWait
		explicitWait = new WebDriverWait(driver, 50);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
					
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
		
	}

//	@Test
	public void TC_03_enoughTime_way_01() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//ExplicitWait đợi cho text Hello World xuất hiện
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
				
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
//	@Test
	public void TC_04_enoughTime_way_02() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		sleepInSecond(1);
		
		//Click vào start button
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//ExplicitWait đợi cho Loading bar biến mất
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
				
		
		//Get text và verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		
	}
	
	
//	@Test
	public void TC_05_ajax_loading() {

		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		sleepInSecond(1);
		
		explicitWait = new WebDriverWait(driver, 60);
		
		//Wait cho Date Picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		//Verify cho selected dates la không có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		//Wait cho ngày 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		//Click vào ngày 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		//Wait cho Ajax icon loading biến mất (invisible)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		
		//Wait cho ngày vừa được chọn có thể clickable trở lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
//		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		//Verify ngày 19 đã được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Saturday, November 19, 2022");
				
		
	}

	@Test
	public void TC_06_goFiles_uploadFiles() {
		
		explicitWait = new WebDriverWait(driver, 60);
		
		driver.get("https://gofile.io/uploadFiles");
		
		//Wait cho Add Files Button được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));
		
		//Load files
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + mountainFilePath);
		
		//Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));
//		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.progress")));
		
		//Wait cho đoạn text 'your files have been successfully uploaded'
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
//		//Wait cho show file button is clickable (step 01: Wait)
//		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
//		
//		//Click vào button show file (Step 02: Click)
//		driver.findElement(By.cssSelector("rowUploadSuccess-showFiles")).click();
		
		//Gộp step 01 & 02 lại thì ta vừa wait xong click như sau:
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		
//		//Wait cho các file name với button download wait hiển thị (step 01: Wait)
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='beach.jpg']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")));
//		
//		//Verify file name với button download hiển thị (Step 02: verify)
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='beach.jpg']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		
		//Gộp step 01 và 02 lại ta vừa wait xong verify như sau:
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + computerFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + mountainFileName + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());

		//Gộp step 01 và 02 lại ta vừa wait xong verify như sau:
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFileName + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + computerFileName + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + mountainFileName + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());

		
		
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
