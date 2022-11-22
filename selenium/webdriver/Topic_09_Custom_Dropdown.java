package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Random rand;
	
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
		
//		khởi tạo wait
		explicitWait = new WebDriverWait(driver,30);
	}

//	@Test
	public void TC_01_jQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		
//		Select a number
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "8");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "8");
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
		
		
//		Select a speed
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		sleepInSecond(3);
		
		
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slow");
		sleepInSecond(3);
		
			}

//	@Test
	public void TC_02_Honda() {
		
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInSecond(10);
		
		scrollToElement("div.carousel-item");
		sleepInSecond(1);
		
		
		selectItemInCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "CITY G");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY G");
		
		scrollToElement("div.container");
		sleepInSecond(1);
		
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Bắc Kạn");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Bắc Kạn");
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");
		
		
		
	}

//	@Test
	public void TC_03_reactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(8);
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Matt");
		
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Justen Kitsune");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Justen Kitsune");
		
		selectItemInCustomDropdown("div.dropdown","div.menu span.text","Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Jenny Hess");
		
		
		
	}
	
//	@Test
	public void TC_04_vueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(8);
		
		selectItemInCustomDropdown("div.btn-group>li","ul.dropdown-menu a","Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(), "Second Option");
		
		selectItemInCustomDropdown("div.btn-group>li","ul.dropdown-menu a","First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(), "First Option");
		
	}
	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(8);
		
		enterItemInCustomDropdown("input.search","div.menu span.text","Afghanistan");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Afghanistan");
		
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
//		click vao bat ky phan tu nao de display
		driver.findElement(By.cssSelector(parentLocator)).click();
		
//		chờ tất cả item trong dropdown được load ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
//		Nếu item cần chọn đang hiển thị
//		Nếu item cần chọn không hiển thị thì cần scroll chuột xuống kiếm
//		Kiểm tra text của item và kiểm tra theo điều kiện
//		=> Viết code duyệt  qua từng item và kiểm tra theo điều kiện
//		Duyệt qua từng item, lấy ra text và kiểm tra nó bằng với text mình mong muốn thì click vào
		
//		Lưu trữ tất cả các item lại thì mới duyệt qua được
		
//		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		
//		Duyệt qua từng item -> dùng vòng lặp
		
		for (WebElement item : allItems) {
			
//			Lấy text ra
			String textActualItem = item.getText();

//			Kiểm tra nếu textItem bằng với text mình mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
				break;
			}
			
		}
		
	}

	
	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
//		click vao bat ky phan tu nao de display
		
		driver.findElement(By.cssSelector(parentLocator)).clear();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);
		sleepInSecond(1);
		
//		chờ tất cả item trong dropdown được load ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
//		Nếu item cần chọn đang hiển thị
//		Nếu item cần chọn không hiển thị thì cần scroll chuột xuống kiếm
//		Kiểm tra text của item và kiểm tra theo điều kiện
//		=> Viết code duyệt  qua từng item và kiểm tra theo điều kiện
//		Duyệt qua từng item, lấy ra text và kiểm tra nó bằng với text mình mong muốn thì click vào
		
//		Lưu trữ tất cả các item lại thì mới duyệt qua được
		
//		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		
//		Duyệt qua từng item -> dùng vòng lặp
		
		for (WebElement item : allItems) {
			
//			Lấy text ra
			String textActualItem = item.getText();

//			Kiểm tra nếu textItem bằng với text mình mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
				break;
			}
			
		}
		
	}


	public void scrollToElement(String cssLocator) {
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
		
	}
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
