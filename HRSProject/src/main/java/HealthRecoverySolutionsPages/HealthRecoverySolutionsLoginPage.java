package HealthRecoverySolutionsPages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.NgWebDriver;

import HealthRecoverySolutionsBase.HealthRecoverySolutionBase;


public class HealthRecoverySolutionsLoginPage extends HealthRecoverySolutionBase {

	// NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
	public boolean loginPassed= false;
	public boolean forgetPwdPassed= false;

	@FindBy(id="loginEmail")
	WebElement username;	

	@FindBy(id="password")
	WebElement password;	


	@FindBy(xpath="//*[@id=\"loginSubmitButton\"]")	
	WebElement loginButton;

	@FindBy(xpath="//*[@id=\"loginPage\"]/div[2]/div/div[1]/div[2]/form/div[4]/a")
	WebElement forgetPwd;

	@FindBy(id="resetPassword_username")
	WebElement resetUsr;

	@FindBy(id="resetPasswordSaveButton")
	WebElement forgetPwdSubmitButton;

	@FindBy(xpath="/html/body/div[2]/div[2]/span")
	WebElement confirmationMsg;

	
	public HealthRecoverySolutionsLoginPage(){
		PageFactory.initElements(driver, this);
	}

	public String validateLoginpageTitle(){
		return driver.getTitle();				
	}

	public boolean emptyLogin() throws InterruptedException {
		if(loginButton.isEnabled()) {
			loginButton.click();
			return loginPassed;
		}
		else return loginPassed = true;
	}


	public boolean invalidLogin(String un,  String pwd) throws InterruptedException{		
		username.sendKeys(un);			
		password.sendKeys(pwd);
		loginButton.click();
		Thread.sleep(1000);		
		String invalid_msg = confirmationMsg.getText();		
		if(invalid_msg.equals("Username and/or password invalid")) {			
			return loginPassed=true;
		}
		else
		{
			return loginPassed;
		}			
	}

	public boolean validateForgetPwd(String usr) throws InterruptedException {
		forgetPwd.click();
		Thread.sleep(2000);
		resetUsr.sendKeys(usr);
		forgetPwdSubmitButton.click();
		Thread.sleep(1000);	
		String resetPwdMsg1 = confirmationMsg.getText();		
		if(resetPwdMsg1.equals("Reset password request successfully submitted.")){
			return forgetPwdPassed = true;
		}
		else {
			return forgetPwdPassed;
		}
	}
	


}

