package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import driver.DriverManager;
import pageEvents.HomePageEvents;
import pageObjects.HomePageElements;
import utilities.CsvReaderOptions;

public class HomePageTests extends BaseTest {

    private final HomePageEvents homepageEvents = new HomePageEvents();

    @Test
    public void verifyPublicationOptions() {
        List<WebElement> options = homepageEvents.getPublicationOptions();
        ArrayList<String> expectedOptions = CsvReaderOptions.getOptions("publicationOptions.csv");
        SoftAssert softAssert = new SoftAssert();

        System.out.println(options.size());
        System.out.print(expectedOptions.size());
        for (int i = 0; i < expectedOptions.size(); i++) {
            WebElement option = options.get(i);
            softAssert.assertEquals(option.getText().trim(), expectedOptions.get(i),
                    "Publication option text does not match at row " + (i));
        }
        softAssert.assertAll();
    }

    @Test
    public void selectAdType() {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);
        WebElement runOnRadio = driver.findElement(HomePageElements.radioRunOn);
        
        action.scrollToElement(runOnRadio).perform();
        wait.until(ExpectedConditions.elementToBeClickable(runOnRadio));
        runOnRadio.click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(runOnRadio.isSelected(), "Run On ad type is not selected");
        softAssert.assertAll();
    }

    @Test
    public void verifyCategoryOptions() {
        List<WebElement> categories = homepageEvents.getCategoryOptions();
        ArrayList<String> expectedCategories = CsvReaderOptions.getOptions("categoryOptions.csv");
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < expectedCategories.size(); i++) {
            WebElement category = categories.get(i+1);
            softAssert.assertEquals(category.getText().trim(), expectedCategories.get(i),
                    "Category text does not match at row " + (i+1));
        }
        softAssert.assertAll();
    }

}
