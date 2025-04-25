package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import io.qameta.allure.*;
import utils.ConfigReader;

public class Test01 extends BaseTest {
    // Initialize the logger for this class
    private static final Logger logger = LoggerFactory.getLogger(Test01.class);
    // Test to open the URL from the config and verify the page title
    @Test
    @Description("Verify that the valid title is displayed on the home page")
    public void openAndVerifyTitle() {
        // HomePage initialization (this happens after BaseTest @BeforeClass)
        HomePage homePage = new HomePage();
        // Open the Home page
        homePage.open();
        // Get the title of the page
        String pageTitle = homePage.getTitle();
        // Log the page title
        logger.info("Page title: {}", pageTitle);
        // Verify the title is correct (make sure the expected title is in the properties file or hardcoded)
        Assert.assertEquals(pageTitle, ConfigReader.getConfigProperty("pageTitle"), "Page title does not match");
        // Log success
        logger.info("Test passed: Page title matches the expected value");
    }
}
