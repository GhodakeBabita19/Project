package com.pomclass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;

import com.utility.Utility;


public class LoginPagePom extends BaseClass{
	
public LoginPagePom(){
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Username : Admin']")
	private WebElement usernameText;

	@FindBy(xpath = "//p[text()='Password : admin123']")
	private WebElement passwordText;

	@FindBy(xpath = "//div/input[@name='username']")
	private WebElement usernameInput;

	@FindBy(xpath = "//div/input[@name='password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@class='orangehrm-login-forgot']//child::p")
	private WebElement forgotpassword;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement resetUsernameInput;

	@FindBy(xpath = "//p[text()='Invalid credentials']")
	private WebElement errorMessage;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement resetPasswordButton;

	@FindBy(xpath = "//h6[text()='Reset Password link sent successfully']")
	private WebElement resetPasswordText;

   
	public String getUsername() {
		String username = Utility.getWebElementText(usernameText);
		return username.substring(11);
	}
	public String getPassword() {
		String password = Utility.getWebElementText(passwordText);
		return password.substring(11);
	}
	public AdminPagePom login(String username,String password) {
		Utility.setText(usernameInput, username);
		Utility.setText(passwordInput, password);
		Utility.webElementClick(submitButton);
		return new AdminPagePom();
	}
	public String getUnvalidUsername() {
		String unvalidUsername = Utility.getWebElementText(usernameText);
		return unvalidUsername.substring(0,8);
	}
	public String getUnvalidPassword() {
		String unvalidPassword = Utility.getWebElementText(passwordText);
		return unvalidPassword.substring(0,8);
	}
	public void unvalidLogin(String unvalidUsername, String unvalidPassword) {
		Utility.setText(usernameInput, unvalidUsername);
		Utility.setText(passwordInput, unvalidPassword);
		Utility.webElementClick(submitButton);
	
	}
	public String getErrorMessage() {
	String message = Utility.getWebElementText(errorMessage);
		return message;
	}
	
	public void forgotPasswordFun(String username) {
		
		Utility.webElementClick(forgotpassword);
		Utility.setText(resetUsernameInput,username);
		Utility.webElementClick(resetPasswordButton);
		
	}
	public String getResetMessage() {
		String resetMessage =Utility.getWebElementText(resetPasswordText);
		return resetMessage;
	}
		
	
	
	
	

}
