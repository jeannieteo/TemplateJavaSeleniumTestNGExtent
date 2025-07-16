package pageEvents;

import java.sql.Driver;
import java.util.List;
import base.BaseTest;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.HomePageElements;

public class HomePageEvents {
	

	public List<WebElement> getPublicationOptions()	{
		List<WebElement> options = base.BaseTest.driver.findElements(HomePageElements.publicationOptions);
		return options;
	
	}

	public boolean isAdTypeSelected(By radioButton) {
			if(base.BaseTest.driver.findElement(radioButton).isSelected()) {
				return true;
			}
		return false;
	}

	public List<WebElement> getCategoryOptions()	{
		List<WebElement> options = base.BaseTest.driver.findElements(HomePageElements.categoryOptions);
		return options;
	
	}
}
