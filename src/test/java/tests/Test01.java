package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;

public class Test01 extends BaseTest {

    // Initialize the logger for this class
    private static final Logger logger = LoggerFactory.getLogger(Test01.class);

    // Test to open the URL from the config and verify the page title
    @Test
    public void openAndVerifyTitle() {
        // Log the start of the test
        logger.info("Starting the test to open the URL and verify the title");

        // Get the base URL from config.properties using ConfigReader
        String baseUrl = ConfigReader.getConfigProperty("baseUrl");

        // Log the URL that is being opened
        logger.info("Opening URL: {}", baseUrl);

        // Open the URL
        driver.get(baseUrl);

        // Wait dynamically for the element to become visible, with a timeout duration
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hotel-logoUrl")));

        // Get the title of the page
        String pageTitle = driver.getTitle();

        // Log the page title
        logger.info("Page title: {}", pageTitle);

        // Verify the title is correct (make sure the expected title is in the properties file or hardcoded)
        Assert.assertEquals(pageTitle, "Restful-booker-platform demo", "Page title does not match");

        // Log success
        logger.info("Test passed: Page title matches the expected value");
    }
}
