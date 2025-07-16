package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;

public class SuiteListener implements ITestListener, IAnnotationTransformer{
	
	public void onTestFailure(ITestResult result) {
    // Take screenshot as Base64
    String base64Screenshot = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64);

    // Attach to Extent Report
    // Assuming you have a way to get the current ExtentTest instance, e.g., ExtentManager.getTest()
    ExtentManager.getTest().fail("Test Failed. Screenshot attached.")
        .addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName());

    // Optionally, still save the screenshot file
    String filename = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName() + ".png";
    File file = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
    try {
        FileUtils.copyFile(file, new File(filename));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
	
	 public void transform(
		  ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		  annotation.setRetryAnalyzer(RetryAnalyzer.class);  
		  }

}
