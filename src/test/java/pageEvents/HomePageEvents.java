package pageEvents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;
import pageObjects.HomePageElements;

public class HomePageEvents {

    public List<WebElement> getPublicationOptions() {
        return BaseTest.getDriver().findElements(HomePageElements.publicationOptions);
    }

    public boolean isAdTypeSelected(By radioButton) {
        return BaseTest.getDriver().findElement(radioButton).isSelected();
    }

    public List<WebElement> getCategoryOptions() {
        return BaseTest.getDriver().findElements(HomePageElements.categoryOptions);
    }
}
