package base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import driver.DriverFactory;
import driver.DriverManager;
import utilities.Constants;
import utilities.ExtentManager;

public class BaseTest {
    /**
     * Runs before any test method in a <test> tag defined in testng.xml. 
     * Setup the extent report
     */
    @BeforeTest
    public void beforeTestMethod() {
        ExtentManager.getExtentReports();
    }
    /**
     * Runs after all test methods in a <test> tag.
     * Flush to print out the report.
     */
    @AfterTest
    public void afterTest() {
        ExtentManager.getExtentReports().flush();
    }

    /**Runs before each test method. 
     * Make a extrent test entry for every @test. 
     * Make a driver instance for every @test
    */
    @Parameters("browser")
    @BeforeMethod
    public void beforeMethodMethod(@Optional("chrome") String browserValue, Method testMethod) {
        ExtentTest extentTest = ExtentManager.getExtentReports().createTest(testMethod.getName());
        ExtentManager.setTest(extentTest);
        DriverManager.setDriver(DriverFactory.createDriver(browserValue));
        DriverManager.getDriver().get(Constants.url);
    }

    /**
     * Runs after each test method.
     * Update status for every @test in the extent report
     * teardown the driver instant after every @test
     */
    @AfterMethod
    public void afterMethod(ITestResult testresult) {
        ExtentTest extentTest = ExtentManager.getTest();
        int result = testresult.getStatus();
        switch (result) {
            case ITestResult.FAILURE:
                extentTest.log(Status.FAIL, MarkupHelper.createLabel(testresult.getName() + " : " + Status.FAIL, ExtentColor.RED));
                break;
            case ITestResult.SKIP:
                extentTest.log(Status.SKIP, MarkupHelper.createLabel(testresult.getName() + " : " + Status.SKIP, ExtentColor.ORANGE));
                break;
            case ITestResult.SUCCESS:
                extentTest.log(Status.PASS, MarkupHelper.createLabel(testresult.getName() + " : " + Status.PASS, ExtentColor.GREEN));
                break;
            default:
                break;
        }
        DriverManager.quitDriver();
        ExtentManager.removeTest();
        }
        
}


