package pageObjects;

import org.openqa.selenium.By;

public class HomePageElements {

    public static final By NAV_BAR = By.id("navbar-nav");
    public static final By PUBLICATION_DROPDOWN = By.id("publicationDDL");
    public static final By publicationOptions = By.cssSelector("#publicationDDL option");
    public static final By categoryOptions = By.cssSelector("#classCatDDL option");
    public static final By radioRunOn = By.cssSelector("#runOnRadio input");
    public static final By radioSemiDisplay = By.cssSelector("#semiRadio input");
    public static final By radioDisplayRadio = By.cssSelector("#displayRadio input");
}
