<h1 align="center">
Test Automation Framework for UI with Selenium
</h1>
<p align="center">
Selenium, Java, Allure, Maven
</p>

<p align="center">
<img src="https://github.com/catalyn98/TestAutomationFrameworkWithSelenium/blob/main/selenium.png" />
</p>

# Tech stack
Test Automation Framework for UI with Selenium uses a number of open source projects to work properly:
* [Selenium](https://www.selenium.dev) - is an open source umbrella project for a range of tools and libraries aimed at supporting browser automation.
* [Java](https://www.java.com/en/) - is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
* [Allure](https://docs.qameta.io/allure/) - is a flexible multi-language test report tool to show you a detailed representation of what has been tested end extract max from the everyday execution of tests.
* [Maven](https://maven.apache.org) - is a build automation tool used primarily for Java projects.

# Prerequisites

* JDK 17 or higher;
* Maven 3.8.3 or higher;
* Allure 2.13 or higher;
* Chrome driver on your machine needs to be the same version as it is in the framework.

# Running the tests

#### Run all tests 
Open up the framework in IntelliJ/Visual Studio Code or similar IDE and just execute in the command line/terminal *mvn clean test*.
This command will delete all rezidual directories generated during previous runs and then will execute all tests.

#### Run a single suite/feature  
The `TestSuites` directory contains various xml files, each one defining the tests and methods which will be executed. 
With the following commands, we will override the default `-Dtarget.testng.suite` variable to use the specified xml file.   
The tests will be executed on Chrome *(currently Selenium supports Chrome, Firefox and Microsoft Edge)*  

Examples:  
```mvn clean test -Dtarget.testng.suite=TestSuites/SmokeTestSuite.xml ```
(runs smoke suite)  
```mvn clean test -Dtarget.testng.suite=TestSuites/SanityTestSuite.xml ```
(runs sanity suite)   
```mvn clean test -Dtarget.testng.suite=TestSuites/CreateAccountTestSuite.xml ```
(runs create account feature)  
```mvn clean test -Dtarget.testng.suite=TestSuites/LoginTestSuite.xml -Dbrowser=firefox ```
(runs login feature)  
```mvn clean test -Dtarget.testng.suite=TestSuites/CheckoutTestSuite.xml ```
(runs order product feature)    

# Reporting

### Allure reports
#### Generate report  
After a suite/feature was executed you can generate a report based on allure-results files: 
*allure serve your path where the project is*. Using the default settings the reports will be generated in allure-results directory. 

## Versioning

Version 1.0

## Authors
Catalan Catalin
