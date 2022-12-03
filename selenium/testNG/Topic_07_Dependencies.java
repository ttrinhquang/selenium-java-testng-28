package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_07_Dependencies {
  @Test
  public void Product_01_Create_product() {
	  Assert.assertTrue(false);
  }
  
  @Test(dependsOnMethods = "Product_01_Create_product")
  public void Product_02_Read_product() {
  }
  
  @Test(dependsOnMethods = "Product_01_Create_product")
  public void Product_03_Update_product() {
  }
  
  @Test(dependsOnMethods = "Product_01_Create_product")
  public void Product_04_Delete_product() {
  }
  
  

}
