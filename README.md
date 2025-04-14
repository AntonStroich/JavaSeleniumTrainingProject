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
   mvn clean install

3. Configure Local Settings:
    - Edit local.properties to set custom environment properties.
      Example:
      baseURL=https://yourwebsite.com
      className=exampleClassName

4. Run Tests:
   You can run tests using Maven with the following command:
   mvn clean test

   To specify a browser, use the -Dbrowser flag:
   mvn clean test -Dbrowser=firefox

TestNG Configuration:
----------------------
TestNG is configured through the testng.xml file, where you can specify which tests to run, pass parameters like browser name, timeouts, etc.

Example testng.xml:

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

