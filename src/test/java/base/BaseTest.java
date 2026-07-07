package base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.Constants;
import utilities.ExtentManager;

public class BaseTest {

    public static WebDriver driver;

    @BeforeTest
    public void beforeTestMethod() {
        ExtentManager.getExtentReports(); // Initializes if not already
        // Optionally, you can modify ExtentManager to accept a custom path if needed
    }

    @AfterTest
    public void afterTest() {
        ExtentManager.getExtentReports().flush();
    }

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethodMethod(String browserValue, Method testMethod) {
        // Create and set ExtentTest for this test method
        ExtentTest extentTest = ExtentManager.getExtentReports().createTest(testMethod.getName());
        ExtentManager.setTest(extentTest);

        // Setup WebDriver
        if (browserValue.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless=new"); // Enable headless mode

            driver = new ChromeDriver(chromeOptions);

        } else if (browserValue.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless=new"); // Enable headless mode
            driver = new FirefoxDriver(firefoxOptions);

        } else if (browserValue.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            
        } else {
            Assert.fail(browserValue + " : not valid browser");
        }
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize(); //maximize window to see whole page
    }

    @AfterMethod
    public void afterMethod(ITestResult testresult) {
        ExtentTest extentTest = ExtentManager.getTest();
        int result = testresult.getStatus();
        if (result == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(testresult.getName() + " : " + Status.FAIL, ExtentColor.RED));
        } else if (result == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(testresult.getName() + " : " + Status.SKIP, ExtentColor.ORANGE));
        } else if (result == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(testresult.getName() + " : " + Status.PASS, ExtentColor.GREEN));
        }
        driver.quit();
        ExtentManager.removeTest();
    }
}