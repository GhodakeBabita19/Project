package com.tests;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.utility.Utility;

public class MyListeners implements  ITestListener{
	
	@Override
    public void onTestSuccess(ITestResult result) {
        try {
            Utility.getScreenShot(result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Utility.getScreenShot(result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
