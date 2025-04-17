package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminLoginPage;
import pages.AdminRoomsPage;
import io.qameta.allure.*;

/**
 * Test02 - Verifies the Admin Rooms page title after successful login.
 */
public class Test02 extends BaseTest {

    // Logger instance for this test class
    private static final Logger logger = LoggerFactory.getLogger(Test02.class);

    /**
     * Test to open the Admin Login Page, perform login using credentials from config,
     * and verify that the title of the Admin Rooms page is correct.
     */
    @Test
    @Description("Verify that the valid title is displayed on the Admin Rooms page")
    public void openAndVerifyTitle() {
        // Initialize AdminLoginPage with driver and wait from BaseTest
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver, wait);

        // Open the Admin Login page
        adminLoginPage.openPage();

        // Log in using credentials from the configuration file
        adminLoginPage.loginAsAdminFromConfig();

        // Initialize AdminRoomsPage after successful login
        AdminRoomsPage roomsPage = new AdminRoomsPage(driver, wait);

        // Retrieve the title of the current page
        String pageTitle = roomsPage.getTitle();

        // Log the retrieved title
        logger.info("Page title: {}", pageTitle);

        // Verify the page title matches the expected value
        Assert.assertEquals(pageTitle, "Restful-booker-platform demo", "Page title does not match");

        // Log success message if assertion passes
        logger.info("Test passed: Page title matches the expected value");
    }
}
