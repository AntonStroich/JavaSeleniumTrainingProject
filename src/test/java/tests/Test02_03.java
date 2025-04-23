package tests;

import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminLoginPage;
import pages.AdminRoomsPage;
import pages.HomePage;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

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
        SoftAssert softAssert = new SoftAssert();
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver, wait);
        adminLoginPage.openPage();
        roomsPage = adminLoginPage.loginWithValidAdminCredentialsFromConfig();
        String pageTitle = roomsPage.getTitle();
        logger.info("Page title after login: {}", pageTitle);
        softAssert.assertEquals(pageTitle, ConfigReader.getConfigProperty("pageTitle"), "Page title does not match after login");
        softAssert.assertTrue(roomsPage.isNavbarDisplayed(), "Navbar is not displayed");
        logger.info("Test passed: Page title matches expected value after login");
        softAssert.assertAll();
    }

    /**
     * Test to log out from Admin and verify the HomePage title after logout.
     */
    @Test(dependsOnMethods = "openAndVerifyTitle")
    @Description("Verify that user is redirected to HomePage after logout and the title is correct")
    public void logoutAndVerifyTitle() {
        HomePage homePage = roomsPage.logout();
        String homeTitle = homePage.getTitle();
        logger.info("Page title after logout: {}", homeTitle);
        Assert.assertEquals(homeTitle, ConfigReader.getConfigProperty("pageTitle"), "Home page title does not match after logout");
        logger.info("Test passed: Home page title matches expected value after logout");
    }
}
