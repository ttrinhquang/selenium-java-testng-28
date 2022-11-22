package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_seleniumAction {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	Actions actions;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
	}

//	@Test
	public void TC_01_hoverToElement_Tooltip() {
		
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		//hover vao element
		actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

//	@Test
	public void TC_02_hoverToElement() {
		
		driver.get("https://www.myntra.com/");
		
//		Hover to element
		
		actions.moveToElement(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"))).perform();
		sleepInSecond(3);
		
		actions.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
		
	}

//	@Test
	public void TC_03_hoverToElement_fahasa() {
		driver.get("https://www.fahasa.com/");
		
		actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']")).click();
		
		
//		actions.click(driver.findElement(By.cssSelector("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']"))).perform();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']//li//strong")).getText(), "KỸ NĂNG SỐNG");
		
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//li//strong[text()='Kỹ năng sống']")).isEnabled());
		
		
		
		
	}
	
//	@Test
	public void TC_04_clickAndHoldElement_selectMultipleItems() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Get 12 elements
		
		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol//li"));
		
		System.out.println("Tổng số lượng number = " + listNumbers.size() );
		
		//click vao so bat dau va chua nha? chuột ra -> di chuyển đến số kết thúc -> nhả chuột ra(release)
		
		actions.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(9)).release().perform();
		sleepInSecond(5);
		
		
		//Verify những element ở trên đã được chọn hay chưa?
		
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		System.out.println("Tổng số lượng number đã được chọn = " + listNumberSelected.size());
		
		Assert.assertEquals(listNumberSelected.size(), 6);
		
		//Veriy những text đã chọn của element
		
		//Define ra 1 mảng chứa các text mình cần verify
		String[] listNumberSelectedActual = {"1", "2", "5", "6", "9", "10"};
		
		//Khai báo ra 1 ArrayList để lưu lại các giá trị được get trong list bên trên
		ArrayList<String> listNumberSelectedExpected = new ArrayList<String>();
		
		//Vòng lặp để duyệt qua list đã chọn ở trên
		
		for (WebElement number : listNumberSelected) {
			
			listNumberSelectedExpected.add(number.getText());
		}
		
		//Ép kiêu Array qua list sau đó verify
		
		Assert.assertEquals(listNumberSelectedExpected, Arrays.asList(listNumberSelectedActual));
	}
	
	//@Test
	public void TC_05_clickAndHoldElement_selectRandomItems() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		

		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol//li"));
		
		System.out.println("Tổng số lượng number = " + listNumbers.size() );
		
		
		// Nhấn phím Ctrl xuống (Chưa nhả ra)
		
		actions.keyDown(Keys.CONTROL).perform();
		
		//Click vao các số cần chọn 2 4 5 7 11
		actions.click(listNumbers.get(1)).click(listNumbers.get(3)).click(listNumbers.get(4)).click(listNumbers.get(6)).click(listNumbers.get(10)).perform();
		
		//Nhã phím Ctrl ra
		
		actions.keyDown(Keys.CONTROL).perform();
		
		//Verify về số element đã chọn
		
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		System.out.println("Tổng số lượng number đã được chọn = " + listNumberSelected.size());
		
		Assert.assertEquals(listNumberSelected.size(), 5);

		}
	
//	@Test
	public void TC_06_doubleClick() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		//Scroll to element trước khi click, do trên Firefox webbrowser hay bị lỗi về viewport
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);
		
		actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		
		
		
	}
	
//	@Test
	public void TC_07_rightClick() {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		sleepInSecond(5);
		
		//Click chuột phải vào button
		
		actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(5);
		
		//Hover chuột vào context menu: Pase
		
		actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		//Verify Paste có 2 class là hover/visible
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		
		
		//Click vào Paste
		actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		//Handle Alert
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		
		//Verify paste co trang thai invisible
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
		
	}
	
//	@Test
	public void TC_08_dragAndDropHTML4() {
		
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		sleepInSecond(4);
		
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		actions.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(5);
		
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		//Verify dua vao background color
		
		String rgbacolor = bigCircle.getCssValue("background-color");
		System.out.println("RGB Color" + rgbacolor);
		

		String hexaColor = Color.fromString(rgbacolor).asHex().toUpperCase();
		System.out.println("RGB Color" + hexaColor);

		Assert.assertEquals(hexaColor, "#03A9F4");
		
		
		
	}
	
	@Test
	public void TC_09_dragAndDropHTML5() throws IOException {
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(4);
		
		String jsContentFile = getContentFile(projectPath + "\\drapAndDrop\\drag_and_drop_helper.js");
		
		jsExecutor.executeScript(jsContentFile + "$(\'div#column-a\').simulateDragDrop({dropTarget:\'div#column-b\'});");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		
		sleepInSecond(3);
		
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
