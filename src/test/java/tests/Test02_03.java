package tests;

import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminLoginPage;
import pages.AdminRoomsPage;
import pages.HomePage;

/**
 * Test02_03 - Includes multiple verifications for the Admin flow.
 */
public class Test02_03 extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(Test02_03.class);
    private AdminRoomsPage roomsPage;

    /**
     * Test to open the Admin Login Page, perform login using credentials from config,
     * and verify that the title of the Admin Rooms page is correct.
     */
    @Test()
    @Description("Verify that the valid title is displayed on the Admin Rooms page")
    public void openAndVerifyTitle() {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver, wait);
        adminLoginPage.openPage();
        adminLoginPage.loginAsAdminFromConfig();

        roomsPage = new AdminRoomsPage(driver, wait);
        String pageTitle = roomsPage.getTitle();
        logger.info("Page title after login: {}", pageTitle);
        Assert.assertEquals(pageTitle, "Restful-booker-platform demo", "Page title does not match after login");
        logger.info("Test passed: Page title matches expected value after login");
    }

    /**
     * Test to log out from Admin and verify the HomePage title after logout.
     */
    @Test(dependsOnMethods = "openAndVerifyTitle")
    @Description("Verify that user is redirected to HomePage after logout and the title is correct")
    public void logoutAndVerifyTitle() {
        roomsPage.logout();

        HomePage homePage = new HomePage(driver, wait);
        String homeTitle = homePage.getTitle();
        logger.info("Page title after logout: {}", homeTitle);
        Assert.assertEquals(homeTitle, "Restful-booker-platform demo", "Home page title does not match after logout");
        logger.info("Test passed: Home page title matches expected value after logout");
    }
}
