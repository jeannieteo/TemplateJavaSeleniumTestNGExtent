package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
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

        assertOptionCount(options, expectedOptions, "publication");
        for (int i = 0; i < expectedOptions.size(); i++) {
            WebElement option = options.get(i + 1);
            softAssert.assertEquals(option.getText().trim(), expectedOptions.get(i),
                    "Publication option text does not match at row " + (i + 1));
        }
        softAssert.assertAll();
    }

    @Test
    public void selectAdType() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(HomePageElements.radioRunOn));

        getDriver().findElement(HomePageElements.radioRunOn).click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homepageEvents.isAdTypeSelected(HomePageElements.radioRunOn),
                "Run On ad type is not selected");
        softAssert.assertAll();
    }

    @Test
    public void verifyCategoryOptions() {
        List<WebElement> categories = homepageEvents.getCategoryOptions();
        ArrayList<String> expectedCategories = CsvReaderOptions.getOptions("categoryOptions.csv");
        SoftAssert softAssert = new SoftAssert();

        assertOptionCount(categories, expectedCategories, "category");
        for (int i = 0; i < expectedCategories.size(); i++) {
            WebElement category = categories.get(i + 1);
            softAssert.assertEquals(category.getText().trim(), expectedCategories.get(i),
                    "Category text does not match at row " + (i + 1));
        }
        softAssert.assertAll();
    }

    private void assertOptionCount(List<WebElement> actualOptions, List<String> expectedOptions, String optionType) {
        Assert.assertTrue(actualOptions.size() > expectedOptions.size(),
                "Expected at least one placeholder plus " + expectedOptions.size() + " " + optionType + " options.");
    }
}
