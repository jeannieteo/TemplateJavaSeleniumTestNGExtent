## Framework STructure
This is a Java(language) + Selenium(library) + TestNg(test library) + Maven(dependaency management) + extent(report tool)

### Folders 
#### src/test/java

##### base  
 ├── BaseTest.java → common setup, teardown, and configuration logic across all test cases.

##### driver   
 ├── DriverFactory.java   → creates browsers.  
 ├── DriverManager.java   → stores, retrieves, and cleans up browser instances.  

##### pagesEvents    → all the actions are housed here  
 ├── HomePageEvent.java  

##### pageObjects  → all the WebElements are housed here  
 ├── HomePageElements.java  
 
##### tests  → extending BaseTest, the actual test class focuses on unique business logic  
 ├── HomePageTests.java  

##### utilities  
 ├── Constants.java  
 ├── CsvReaderOptions.java  
 ├── RetryAnalyzer.java
 ├── ExtentManager.java  
 ├── SuiteListener.java  
