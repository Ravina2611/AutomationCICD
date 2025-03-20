package ravinaprojects.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ravinaprojects.resources.ExtentReporterNG;

//import org.testng.ITestListener;

public class Listeners extends Basetest implements ITestListener  {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	@Override
    public void onTestStart(ITestResult result) {
		test = 	extent.createTest(result.getMethod().getMethodName());
		//extentTest.set(test);
        //System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        test.log(Status.PASS,"Test Passed");
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        test.fail(result.getThrowable());
        
        try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String FilePath = null;
		try {
			FilePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.addScreenCaptureFromBase64String(FilePath,result.getMethod().getMethodName());
        
        
        
        //screenshot
        
        
        
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    	extent.flush();
        System.out.println("All Tests Finished!");
	
}
}