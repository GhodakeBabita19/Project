package com.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pomclass.AdminPagePom;
import com.pomclass.LoginPagePom;
import com.utility.ExcelReader;
import com.utility.ExtentReportUtility;

@Listeners(MyListeners.class)
public class AdminPageTest extends BaseClass {

	
@BeforeClass
	public void setup() {
		ExtentReportUtility.extentReportInitialisation();

	}
@AfterMethod
	public void teardown() {
		ExtentReportUtility.extentReportgeneration();
	}

@AfterMethod
public void attachlogs(ITestResult result) {
	result.getStatus();
	ExtentReportUtility.logger.info("TestCase  is pass");
	ExtentReportUtility.logger.addScreenCaptureFromPath(projectpath+"/Screenshot/"+result.getName()+".png");
	
	
}

	@Test(groups = { "addUser" }, dependsOnGroups = { "adminLogin" })
	public void addUserTest() throws IOException {
		
		ExtentReportUtility.extentReportCreateTest("addUserTest");

		AdminPagePom adminPagePom = new AdminPagePom();
		adminPagePom.gotoAdminPage();
		adminPagePom.addUser();

		Map<String, Object> userData = getExcelSheetData();
		
		Assert.assertEquals(userData.get("User Role").toString(),"ESS");
	
		
		adminPagePom.selectUserRole(userData.get("User Role").toString());

		adminPagePom.setEmployeeName(userData.get("Employee Name").toString());

		Assert.assertEquals(userData.get("Status").toString(),"Enabled");
		adminPagePom.setUserStatus(userData.get("Status").toString());

		adminPagePom.setUserDetails(userData.get("User Name").toString(), userData.get("Password").toString(),
				userData.get("Confirm Password").toString());

	}

	public Map<String, Object> getExcelSheetData() throws EncryptedDocumentException, IOException {
		FileInputStream file = ExcelReader.excelSheetRead("User_Details.xlsx");
		Sheet sh = ExcelReader.getSheet(file, "Add User");

		Map<String, Object> rowData = ExcelReader.getRowData(sh, 1);
		System.out.println(rowData);
		return rowData;
	}
}
