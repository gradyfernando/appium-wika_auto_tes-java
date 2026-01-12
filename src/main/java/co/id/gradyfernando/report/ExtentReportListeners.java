package co.id.gradyfernando.report;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import co.id.gradyfernando.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;

public class ExtentReportListeners extends AppiumUtils implements ITestListener {
	
	ExtentReports extent = ExtentReportManager.getExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	AppiumDriver driver;
	
	@Override
    public void onTestStart(ITestResult result) {
//		test = extent.createTest((String) result.getMethod().getMethodName());

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	test.get().fail("Test Fail:" + result.getThrowable());
    	
    	try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	    	test.get().log(Status.FAIL, "Get Driver FAIL: " + e.getMessage());
		}
    	
    	try {
    		String testScreenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
			test.get().addScreenCaptureFromPath(testScreenshotPath, result.getMethod().getMethodName());
		} catch (IOException e) {
	    	test.get().log(Status.FAIL, "Screenshot FAIL: " + e.getMessage());
		}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	test.get().skip("Test Skiped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    
}
