package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        ExtentTest test = ExtentManager.getTest();
        if (!(driver instanceof TakesScreenshot) || test == null) {
            return;
        }

        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        String testName = result.getMethod().getMethodName();
        String base64Screenshot = screenshotDriver.getScreenshotAs(OutputType.BASE64);

        test.fail("Test failed. Screenshot attached.")
                .addScreenCaptureFromBase64String(base64Screenshot, testName);

        File screenshotDirectory = new File(System.getProperty("user.dir"), "screenshots");
        File screenshotFile = new File(screenshotDirectory, testName + ".png");
        try {
            FileUtils.forceMkdir(screenshotDirectory);
            FileUtils.copyFile(screenshotDriver.getScreenshotAs(OutputType.FILE), screenshotFile);
        } catch (IOException e) {
            test.warning("Unable to save screenshot file: " + e.getMessage());
        }
    }

    @Override
    public void transform(
            ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
