package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_FindElement_FindElements {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		rand = new Random();
		
		//Đang apply 15s cho việc tìm elment
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_findElemnt() {
		
		//Case 01
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy và thao tác trực tiếp lên node đó -> Vì nó tìm thấy nên k cần chờ hết timeout 15s
		
		
		
		
		//Case 02
		//Tìm thấy nhiều hơn 1 element/node
		//Nó sẽ thao tác với node đầu tiên, mà không quan tâm các node còn lại
		
		
		//Case 03
		//Không tìm thấy element nào: Nếu hết thời gian mà vẫn k tìm thấy element thì 
		//+ Đánh fail testcase tại step này + k chạy step tiếp theo
		//+ Throw ra 1 ngoại lệ: NoSuchElementException
		
	}

	@Test
	public void TC_02_findElements() {
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy và lưu nó vào list = 1 element -> Vì nó tìm thấy nên k cần chờ hết timeout 15s
		
		
		//Tìm thấy nhiều hơn 1 element/node
		//Tìm thấy và lưu nó vào list bằng số element tương ứng
		
		//Case 03
		//Không tìm thấy element nào: Nếu hết thời gian mà vẫn k tìm thấy element thì 
		//+Không đánh fail testcase, vẫn chạy step tiếp theo
		//+Trả về 1 list rỗng = 0
				
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
