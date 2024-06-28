package com.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.pomclass.LoginPagePom;
import com.utility.ExtentReportUtility;
import com.utility.Utility;

@Listeners(MyListeners.class)
public class LoginPageTest extends BaseClass {
	
	@BeforeTest

	public void setup() {
		launchWeb();
		ExtentReportUtility.extentReportInitialisation();
	}

	@AfterMethod
	public void teardown() {
		
		ExtentReportUtility.extentReportgeneration();
	}
	@AfterMethod
	public void attachErrorLogs(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			ExtentReportUtility.logger.log(Status.FAIL,result.getThrowable());
			ExtentReportUtility.logger.addScreenCaptureFromPath(projectpath+"/Screenshot/"+result.getName()+".png");
		}
		
	}

	@Test()
	public void pageUrl() {
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test(groups= {"adminLogin"})
	public void loginPageTest() {
		ExtentReportUtility.extentReportCreateTest("loginPageTest");
		LoginPagePom loginPagePom = new LoginPagePom();
		
		ExtentReportUtility.logger.info("username"+loginPagePom.getUsername());
        ExtentReportUtility.logger.info("password"+loginPagePom.getPassword());
		//Assert.assertEquals(loginPagePom.getUsername(), "Admin1");
		Assert.assertEquals(loginPagePom.getUsername(), "Admin1");
		Assert.assertEquals(loginPagePom.getPassword(), "admin123");

		loginPagePom.login(loginPagePom.getUsername(), loginPagePom.getPassword());
	}

	@Test()

	public void unvalidLoginTest() {
		LoginPagePom unvalidLoginPom = new LoginPagePom();

		Assert.assertEquals(unvalidLoginPom.getUnvalidUsername(), "Username");
		Assert.assertEquals(unvalidLoginPom.getUnvalidPassword(), "Password");

		unvalidLoginPom.unvalidLogin(unvalidLoginPom.getUnvalidUsername(), unvalidLoginPom.getUnvalidPassword());
		Assert.assertEquals(unvalidLoginPom.getErrorMessage(), "Invalid credentials");

	}

	@Test(enabled = false)
	public void resetPasswordTest() throws IOException {
		LoginPagePom loginPagePom = new LoginPagePom();
		Assert.assertEquals(loginPagePom.getUsername(), "Admin");
		loginPagePom.forgotPasswordFun("Admin");
		
		
       Assert.assertEquals(loginPagePom.getResetMessage(), "Reset Password link sent successfully");
      // Utility.getScreenShot("LoginPageTest");
	}

}
