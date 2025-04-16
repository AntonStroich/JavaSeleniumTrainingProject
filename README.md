Selenium Test Automation Project

This is a sample test automation project using Selenium, TestNG, Maven, and WebDriverManager.
It provides an organized and maintainable structure for writing and running Selenium WebDriver tests.

Project Structure:
----------------------
<pre> ```plaintext .
.
├── src
│   ├── main
│   │   └── java
│   │       └── utils
│   │           └── ConfigReader.java        # Configuration utility for reading from local.properties
│   │           └── Timeouts.java            # Enum for different timeout configurations (SHORT, MEDIUM, LONG)
│   └── test
│       ├── java
│       │   └── tests
│       │       ├── BaseTests.java          # Base class with WebDriver setup and teardown
│       │       └── Test01.java             # Example test class
│       └── resources
│           └── testng.xml                  # TestNG suite configuration file
├── .gitignore                               # Git ignore file to exclude unnecessary files
├── local.properties                        # Store environment-specific configurations
├── pom.xml                                  # Maven project configuration
└── README.md                                # Project description and setup instructions
``` </pre>

Project Overview:
------------------------
This project demonstrates how to set up a simple test automation framework using Selenium and TestNG with dynamic browser support, configurable timeouts, and the use of local.properties for environment-specific settings.

Key Features:
---------------
1. Cross-browser Testing:
    - The framework supports testing on multiple browsers like Chrome, Firefox, Edge, and Safari.
    - The browser can be selected dynamically through the testng.xml file or system properties.

2. Dynamic Waits:
    - Utilizes WebDriver's explicit waits to ensure elements are available before interacting with them.
    - You can customize wait times using the Timeouts enum class.

3. Configurable Test Setup:
    - Uses ConfigReader class to read environment-specific configurations (like URLs, browser names, etc.) from local.properties.
    - Allows you to customize the configuration without modifying the code.

4. Easy to Extend:
    - The project follows a modular design, allowing you to easily add more test cases, browser support, or custom configurations.

Getting Started:
--------------------
Prerequisites:
---------------
Before running the tests, ensure the following:

1. Java 17 (or any compatible version) is installed.
2. Maven is installed and properly configured.
3. IDE (e.g., IntelliJ IDEA, Eclipse) for managing the project.

Setup Instructions:
--------------------
1. Clone the repository:
   git clone <repository-url>
   cd <project-folder>

2. Install Dependencies:
   Maven will automatically download the necessary dependencies like Selenium, TestNG, WebDriverManager, etc.
   mvn clean install -Pskip-tests

3. Configure Local Settings:
    - Edit local.properties to set custom environment properties.
      Example:
      username=your_username

## Running Tests with Maven

This project uses Maven for building and running tests. By default, tests are executed during the `mvn clean install` command. However, you can skip tests during the build process if you don't want them to run by using a custom Maven profile.

### Skipping Tests

To **skip tests** during the `mvn clean install` command, use the `skip-tests` profile. This profile is configured to skip the execution of tests by using the `maven-surefire-plugin`.

#### Command to skip tests:

```bash
mvn clean install -Pskip-tests

## Logging in the Project
This project uses SLF4J with Logback for logging during test execution. Logs provide insights into test flow, debugging, and error handling.

### Logger Setup
The logger is initialized in each class using:
private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

### Logging Levels
INFO: General information (e.g., setup and teardown).
DEBUG: Detailed execution info for debugging.
ERROR: Logs error messages when something goes wrong.

#### Example
logger.info("Starting test execution...");
logger.error("An error occurred: {}", errorMessage);

### Logback Configuration
Logging behavior is controlled via the logback.xml file located in src/main/resources. By default, logs are printed to the console, but this can be changed to log to a file.

```bash
mvn clean install -Pskip-tests
```

## Allure Report
Allure is a framework for generating beautiful, interactive test reports.

### Commands
Run tests: mvn clean test
Generate report: mvn allure:report
View report: mvn allure:serve

## TestNG Configuration:
----------------------
TestNG is configured through the testng.xml file, where you can specify which tests to run, pass parameters like browser name, timeouts, etc.

Example testng.xml:

```markdown
```xml
<?xml version="1.0" encoding="UTF-8"?>
<suite name="SeleniumTestSuite">
    <test name="SeleniumTests">
        <!-- Parameters to pass to the tests (browser name, timeout, etc.) -->
        <parameter name="browser" value="chrome"/>
        <!-- Specify your test classes here -->
        <classes>
            <class name="tests.Test01"/>
        </classes>
    </test>
</suite>
```