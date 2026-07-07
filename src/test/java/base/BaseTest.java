package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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

import utilities.Constants;
import utilities.ExtentManager;

public class BaseTest {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);

    @BeforeTest
    public void beforeTestMethod() {
        ExtentManager.getExtentReports();
    }

    @AfterTest
    public void afterTest() {
        ExtentManager.getExtentReports().flush();
    }

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethodMethod(@Optional("chrome") String browserValue, Method testMethod) {
        ExtentTest extentTest = ExtentManager.getExtentReports().createTest(testMethod.getName());
        ExtentManager.setTest(extentTest);

        WebDriver driver = createDriver(browserValue);
        DRIVER.set(driver);
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
        driver.manage().window().maximize();
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
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
        ExtentManager.removeTest();
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    private WebDriver createDriver(String browserValue) {
        String browser = browserValue == null ? "chrome" : browserValue.trim().toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                return new EdgeDriver(edgeOptions);
            default:
                Assert.fail(browserValue + " is not a valid browser. Use chrome, firefox, or edge.");
                return null;
        }
    }
}
