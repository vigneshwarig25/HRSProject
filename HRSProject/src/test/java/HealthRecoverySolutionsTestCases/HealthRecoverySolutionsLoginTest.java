package HealthRecoverySolutionsTestCases;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import HealthRecoverySolutionsBase.HealthRecoverySolutionBase;
import HealthRecoverySolutionsPages.HealthRecoverySolutionsLoginPage;


public class HealthRecoverySolutionsLoginTest extends HealthRecoverySolutionBase {
	HealthRecoverySolutionsLoginPage loginpage;

	public HealthRecoverySolutionsLoginTest(){
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginpage = new HealthRecoverySolutionsLoginPage();


	}

	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginpage.validateLoginpageTitle();		
		Assert.assertEquals(title, "HRS | ClinicianConnect");
	}


	@Parameters({ "username", "password" })
	@Test(priority=2)
	public void verifyloginButtonTest(String username, String password) throws InterruptedException{
		boolean condition = loginpage.invalidLogin(username, password);
		Assert.assertTrue(condition);

	}


	@Test(priority=3)
	public void verifyEmptyCredentials() throws InterruptedException {
		boolean condition = loginpage.emptyLogin();		
		Assert.assertTrue(condition);
	}


	@Test(priority=4)
	public void verifyForgetPwdButton() throws InterruptedException {
		boolean condition = loginpage.validateForgetPwd(prop.getProperty("forgetPwdUsr"));
		Assert.assertTrue(condition);		
	}

	
	@AfterMethod
	public void tearDown(){
		driver.quit();

	}
}