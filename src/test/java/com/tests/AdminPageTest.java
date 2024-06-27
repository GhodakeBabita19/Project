package com.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pomclass.AdminPagePom;
import com.pomclass.LoginPagePom;
import com.utility.ExcelReader;


@Listeners(MyListeners.class)
public class AdminPageTest extends BaseClass {

	@BeforeClass

	public void setup() {
		launchWeb();

	}

	public void teardown() {
	}

	@Test
 public void addUserTest() throws IOException {
    	
    	
        LoginPagePom loginPagePom = new LoginPagePom();
        Assert.assertEquals(loginPagePom.getUsername(),"Admin");
        Assert.assertEquals(loginPagePom.getPassword(),"admin123");
        
        AdminPagePom adminPagePom = loginPagePom.login(loginPagePom.getUsername(), loginPagePom.getPassword());
        adminPagePom.gotoAdminPage();
        adminPagePom.addUser();
        
    Map<String,Object> userData =   getExcelSheetData();
    System.out.println(userData.get("User Role").toString());
        adminPagePom.selectUserRole(userData.get("User Role").toString());
        
        adminPagePom.setEmployeeName(userData.get("Employee Name").toString());
      
        System.out.println(userData.get("Status").toString());
        adminPagePom.setUserStatus(userData.get("Status").toString());
        
        adminPagePom.setUserDetails(userData.get("User Name").toString(),
                userData.get("Password").toString(),userData.get("Confirm Password").toString());


	}
	public Map<String,Object> getExcelSheetData() throws EncryptedDocumentException, IOException {
	FileInputStream	file = ExcelReader.excelSheetRead("User_Details.xlsx");
	 Sheet sh = ExcelReader.getSheet(file, "Add User");
	 
	 Map<String ,Object> rowData = ExcelReader.getRowData(sh, 1);
	 System.out.println(rowData);
	 return rowData;
	}
}
