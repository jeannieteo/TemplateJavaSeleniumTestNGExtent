package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.BaseTest;
import pageEvents.HomePageEvents;
import pageObjects.HomePageElements;
import java.util.ArrayList;
import utilities.CsvReaderOptions;
import org.testng.asserts.SoftAssert;

public class HomePageTests extends BaseTest{
	
	HomePageEvents homepageEvents = new HomePageEvents();
	HomePageElements homepageElements = new HomePageElements();
	CsvReaderOptions csvReaderOptions = new CsvReaderOptions();
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void verifyPublicationOptions() {
		List<WebElement> options = homepageEvents.getPublicationOptions();
		ArrayList<String> optionsText = csvReaderOptions.getOptions("src/main/resources/publicationOptions.csv"); //get the categories from the testdata file
		int i =0;
		for(WebElement option:options) {
			softAssert.assertEquals(option.getText(), optionsText.get(i), "Option text does not match expected value: " + option.getText());
			i++;
			}
	}

	@Test
	public void selectAdType() {
		driver.findElement(HomePageElements.radioRunOn).click();
		softAssert.assertTrue(homepageEvents.isAdTypeSelected(HomePageElements.radioRunOn), "Run On ad type is not selected");
	}

	@Test
	public void verifyCategoryOptions() {
		List<WebElement> categories = homepageEvents.getCategoryOptions();
		ArrayList<String> categoriesText = csvReaderOptions.getOptions("src/main/resources/categoryOptions.csv");
		
		int i = 0;
		for(WebElement category : categories) {
			System.out.println(category.getText().trim());
			if(i != 0)	{
;			softAssert.assertEquals(category.getText().trim(), categoriesText.get(i), "Category text does not match expected value: " + category.getText());}
			i++;
		}
		softAssert.assertAll(); // Assert all at the end to report all failures
	}

	@Test
	public void verifyCategoryOptions1() {
		List<WebElement> categories = homepageEvents.getCategoryOptions();
		ArrayList<String> categoriesText = csvReaderOptions.getOptions("src/main/resources/categoryOptions.csv");
		
		int i = 0;
		for(WebElement category : categories) {
			System.out.println(category.getText().trim());
			softAssert.assertEquals(category.getText().trim(), categoriesText.get(i), "Category text does not match expected value: " + category.getText());
			i++;
		}
		softAssert.assertAll(); // Assert all at the end to report all failures
	}

	
}
