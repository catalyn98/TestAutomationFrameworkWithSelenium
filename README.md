<h1 align="center">
    Test Automation Framework for UI with Selenium
</h1>
<p align="center">
    Java, Selenium, Maven, Allure 
</p>

<p align="center">
    <img src="https://github.com/catalyn98/TestAutomationFrameworkWithSelenium/blob/main/selenium.png" />
</p>

# Tech stack
Test Automation Framework for UI with Selenium uses a number of open source projects to work properly:
* [Java](https://www.java.com/en/) - is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
* [Selenium](https://www.selenium.dev) - is an open source umbrella project for a range of tools and libraries aimed at supporting browser automation.
* [Maven](https://maven.apache.org) - is a build automation tool used primarily for Java projects.
* [Allure](https://docs.qameta.io/allure/) - is a flexible multi-language test report tool to show you a detailed representation of what has been tested end extract max from the everyday execution of tests.

# Prerequisites
* JDK 17 or higher;
* Maven 3.8.3 or higher;
* Allure 2.13 or higher;
* Chrome driver on your machine needs to be the same version as it is in the framework.

# Running the tests
#### Run all tests 
Open up the framework in IntelliJ IDEA/Visual Studio Code or similar IDE and just execute in the command line/terminal: 
- `mvn clean test`. This command removes any previously compiled classes, resources, and artifacts from previous builds. This ensures a clean build environment, removing any potential remnants from previous builds and executing the tests.
This command will delete all rezidual directories generated during previous runs.

#### Run a single suite/feature  
The `TestSuites` directory contains various xml files, each one defining the tests and methods which will be executed. 
With the following commands, we will override the default `-Dtarget.testng.suite` variable to use the specified xml file. The tests will be executed on Chrome. Currently Selenium supports Chrome, Firefox and Microsoft Edge. 

#### Examples:  
- `mvn clean test -Dtarget.testng.suite=TestSuites/SmokeTestSuite.xml ` - runs smoke suite;
- `mvn clean test -Dtarget.testng.suite=TestSuites/SanityTestSuite.xml ` - runs sanity suite;
- `mvn clean test -Dtarget.testng.suite=TestSuites/CreateAccountTestSuite.xml ` - runs create account feature;
- `mvn clean test -Dtarget.testng.suite=TestSuites/LoginTestSuite.xml` - runs login feature;
- `mvn clean test -Dtarget.testng.suite=TestSuites/CheckoutTestSuite.xml -Dbrowser=firefox` - runs order product feature. `Dbrowser=firefox` is used to specify the desired browser for running automated tests. By setting `-Dbrowser=firefox`, you are instructing the automation framework to use *Mozilla Firefox* as the target browser for test execution. 

# Reporting
### Allure reports
Allure is a reporting framework that provides detailed and visually appealing reports for test automation frameworks. It can be integrated with various testing tools, including Maven, to generate comprehensive reports for test execution results.
#### Generate report  
To generate Allure reports after running your tests with Maven, you need to perform the following steps:
1. Ensure that the Allure plugin is added to your Maven project's configuration. In the <plugins> section of your project's *pom.xml* file, add the following plugin:
```
  <plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.10.0</version>
  </plugin>
```
2. Once you have the Allure plugin configured, run the tests using the command: `mvn clean test`, this command will compile the source code, run the tests, and generate the test execution results.
3. After the tests are executed, you can generate the Allure report by running the following command: `mvn allure:report`, this command invokes the Allure Maven plugin and generates the HTML report based on the test execution results.
4. Once the report generation is complete, you can view the Allure report by running the command: `mvn allure:serve`, this command launches a local web server and opens the generated report in your default web browser. You can navigate through the report to view detailed information about the test results, including test statuses, execution times, and any attached screenshots or log messages.
<p>By using Allure reports, you can gain valuable insights into the test execution results, identify failed tests, and analyze test trends over time, making it easier to track the progress of your test automation efforts.</p>
