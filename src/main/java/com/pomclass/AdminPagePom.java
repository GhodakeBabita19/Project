package com.pomclass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utility.Utility;

public class AdminPagePom extends BaseClass {

	{

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a/span[text()='Admin']")
	private WebElement adminPageLink;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement username;

	@FindBy(xpath = "(//div[contains(text(),'-- Select --')])[1]")
	private WebElement userroleselect;

	@FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-text-input')]//input")
	private WebElement employeename;

	@FindBy(xpath = "(//div[contains(text(),'-- Select --')])[2]")
	private WebElement statusselect;

	@FindBy(xpath = "//button[text()=' Reset ']")
	private WebElement reset;

	@FindBy(xpath = "//button[text()=' Search ']")
	private WebElement search;

	@FindBy(xpath = "//button[text()=' Add ']")
	private WebElement adduserpage;

	@FindBy(xpath = "(//div[contains(text(),'-- Select --')])[1]")
	private WebElement adduserrole;

	@FindBy(css = "div[role='option'] span")
	private List<WebElement> roleSelect;

	@FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-text-input')]//input")
	private WebElement addemployeename;

	@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
	private WebElement addstatus;

	@FindBy(css = "div[class ='oxd-select-option'] span")
	private List<WebElement> statusSelect;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement addusername;

	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement addpassword;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement addconfirmpassword;

	@FindBy(xpath = "//button[text()=' Cancel ']")
	private WebElement addcancelbutton;

	@FindBy(xpath = "//button[text()=' Save ']")
	private WebElement addsavebutton;

	public void gotoAdminPage() {
		Utility.webElementClick(adminPageLink);
	}

	public void addUser() {
		Utility.webElementClick(adduserpage);
	}

	public void selectUserRole(String role) {

		Utility.getActionClass().clickAndHold(adduserrole)
		.pause(Duration.ofSeconds(1))
		.build()
		.perform();

		for (WebElement selectRole : roleSelect) {
			if (selectRole.getText().equalsIgnoreCase(role)) {
				Utility.webElementClick(selectRole);
			}

		}
	}
	public void setEmployeeName(String empName) {
		Utility.setText(addemployeename,empName );
		
	}
	
		 public void setUserStatus(String userStatus){
	        Utility.getActionClass().clickAndHold(addstatus)
	        .pause(Duration.ofSeconds(1)).build().perform();

	        for ( WebElement selectStatus : statusSelect){
	            if (selectStatus.getText().equalsIgnoreCase(userStatus)){
	                Utility.webElementClick(selectStatus);
	                break;
	            }
	
	        }
		 }

	
	public void setUserDetails(String username, String pass, String confPass) {
		//Utility.applyExplicitWait(statusselect);
		Utility.applyExplicitWait(addusername);
		Utility.setText(addusername, username);
		Utility.setText(addpassword, pass);
		Utility.setText(addconfirmpassword, confPass);
		Utility.webElementClick(addsavebutton);
	}

	public void cancelUserDetails(String username, String pass, String confPass) {
		Utility.setText(addusername, username);
		Utility.setText(addpassword, pass);
		Utility.setText(addconfirmpassword, confPass);
		Utility.webElementClick(addcancelbutton);
	}

}