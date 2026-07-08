package pageEvents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import driver.DriverManager;
import pageObjects.HomePageElements;

public class HomePageEvents {

    public List<WebElement> getPublicationOptions() {
        return DriverManager.getDriver().findElements(HomePageElements.publicationOptions);
    }

    public boolean isAdTypeSelected(By radioButton) {
        return DriverManager.getDriver().findElement(radioButton).isSelected();
    }

    public List<WebElement> getCategoryOptions() {
        return DriverManager.getDriver().findElements(HomePageElements.categoryOptions);
    }
}
