package pageObjects;

import org.openqa.selenium.By;

public class HomePageElements {
	
	public By navBarId = By.id("navbar-nav");
	public By publicationDropdown = By.id("publicationDDL");
	public static By publicationOptions = By.cssSelector("#publicationDDL option");
	public static By categoryOptions = By.cssSelector("select#classCatDDL");
	public static By radioRunOn = By.cssSelector("#runOnRadio input");
	public static By radioSemiDisplay = By.cssSelector("#semiRadio input");
	public static By radioDisplayRadio= By.cssSelector("#displayRadio input");

}
