## Framework STructure
This is a Java(language) + Selenium(library) + TestNg(test library) + Maven(dependaency management) + extent(report tool)

### Folders 
#### src/test/java

base
 ├── BaseTest.java

driver
 ├── DriverFactory.java
 ├── DriverManager.java

pages
 ├── LoginPage.java
 ├── HomePage.java

utilities
 ├── ConfigReader.java
 ├── ScreenshotUtil.java
 ├── WaitUtils.java
 ├── ExtentManager.java

listeners
 ├── TestListener.java

retry
 ├── RetryAnalyzer.java
