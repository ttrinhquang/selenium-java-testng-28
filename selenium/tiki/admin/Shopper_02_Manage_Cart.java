package tiki.admin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Shopper_02_Manage_Cart {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest(alwaysRun = true)
	public void initBrowser() {
		
		System.out.println("=======Open Browser and driver===========");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	  @Test(groups = {"admin", "cart"})
	  public void Product_01_Create_visa() {
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Product_02_View_visa() {
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Product_03_Update_visa() {
	  }
	  
	  @Test(groups = {"admin", "cart"})
	  public void Product_04_Delete_visa() {
	  }
	  
		@AfterTest(alwaysRun = true)
		public void cleanBrowser() {
			
			System.out.println("=======Close Browser and driver===========");
			driver.quit();
			
		}
  

}
