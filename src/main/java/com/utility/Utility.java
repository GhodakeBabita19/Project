package com.utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.BaseClass;

public class Utility extends BaseClass {
	
public static String getWebElementText(WebElement ele) {
		
		return ele.getText();
		
	}
	public static void setText(WebElement ele, String text) {
		
		 ele.sendKeys(text);
		
	}
	public static void webElementClick(WebElement ele) {
		ele.click();
	}
	  public static void setImplicitWeight(Duration sec){
	        driver.manage().timeouts().implicitlyWait(sec);
	    }

	    public static Actions getActionClass(){
	        return new Actions(driver);
	    }
	    
	    public static void getScreenShot(String testName) throws IOException {
	    File screenshotFile	 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String destinationPath = projectpath+"/Screenshot/"+testName+".png";
	    FileUtils.copyFile(screenshotFile,new File(destinationPath));
	    }



}
