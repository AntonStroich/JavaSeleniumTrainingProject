package pages;

import components.AdminNavbar;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base page for all Admin-related pages.
 * Provides shared functionality and common components such as the Admin Navbar.
 */
public abstract class AdminBasePage extends BasePage {

    protected final AdminNavbar navbar;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AdminBasePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.navbar = new AdminNavbar(driver);
    }

    /**
     * Provides access to the Admin navbar component.
     * Use it to interact with shared admin UI elements such as Logout.
     *
     * @return AdminNavbar instance
     */
    public AdminNavbar getNavbar() {
        return navbar;
    }

    /**
     * Checks if the Admin navbar is displayed on the page.
     *
     * @return true if the navbar is visible; false otherwise.
     */
    @Step("Check if Admin navbar is displayed")
    public boolean isNavbarDisplayed() {
        boolean displayed = navbar.isDisplayed();
        logger.info("Navbar isDisplayed: {}", displayed);
        return displayed;
    }


    /**
     * Logs out the current admin user by clicking the Logout button in the navbar.
     * Redirects to the Home page after logout.
     */
    @Step("Logout from Admin")
    public void logout() {
        navbar.clickLogoutBtn();  // Call the logout button click from AdminNavbar
        HomePage homePage = new HomePage(driver, wait);
        homePage.waitForPageToLoad();
    }
}

