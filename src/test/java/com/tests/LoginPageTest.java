package com.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pomclass.LoginPagePom;
import com.utility.Utility;

@Listeners(MyListeners.class)
public class LoginPageTest extends BaseClass {
	
	@BeforeClass

	public void setup() {
		launchWeb();
	}

	
	public void teardown() {
	}

	@Test
	public void pageUrl() {
		Assert.assertEquals(driver.getCurrentUrl(),
				"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test
	public void loginPageTest() {
		LoginPagePom loginPagePom = new LoginPagePom();

		Assert.assertEquals(loginPagePom.getUsername(), "Admin");
		Assert.assertEquals(loginPagePom.getPassword(), "admin123");

		loginPagePom.login(loginPagePom.getUsername(), loginPagePom.getPassword());
	}

	@Test

	public void unvalidLoginTest() {
		LoginPagePom unvalidLoginPom = new LoginPagePom();

		Assert.assertEquals(unvalidLoginPom.getUnvalidUsername(), "Username");
		Assert.assertEquals(unvalidLoginPom.getUnvalidPassword(), "Password");

		unvalidLoginPom.unvalidLogin(unvalidLoginPom.getUnvalidUsername(), unvalidLoginPom.getUnvalidPassword());
		Assert.assertEquals(unvalidLoginPom.getErrorMessage(), "Invalid credentials");

	}

	@Test
	public void resetPasswordTest() throws IOException {
		LoginPagePom loginPagePom = new LoginPagePom();
		Assert.assertEquals(loginPagePom.getUsername(), "Admin");
		loginPagePom.forgotPasswordFun("Admin");
		
		
       Assert.assertEquals(loginPagePom.getResetMessage(), "Reset Password link sent successfully");
      // Utility.getScreenShot("LoginPageTest");
	}

}
