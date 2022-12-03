package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Priority {

	@Test(description = "retest Jira_0787")
	public void EndUser_01_Register_New_Employee(){
		System.out.println("Testcase 01");
		
	}
	
	@Test()
	public void EndUser_02_View_Employee(){
		System.out.println("Testcase 02");
		
	}
	
	@Test(enabled=false)
	public void EndUser_03_Edit_Employee(){
		System.out.println("Testcase 02");
		
	}
	
	@Test()
	public void EndUser_04_Move_Employee(){
		System.out.println("Testcase 02");
		
	}
	
 
}
