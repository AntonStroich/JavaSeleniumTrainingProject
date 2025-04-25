package pages;

import components.AdminNavbar;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base page for all Admin-related pages.
 * Provides shared functionality and common components such as the Admin Navbar.
 */
public abstract class AdminBasePage extends BasePage {

    protected final AdminNavbar navbar;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AdminBasePage() {
        super();
        this.navbar = new AdminNavbar();
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
     * Logs out the currently logged-in admin by clicking the Logout button in the navigation bar.
     * After logout, waits for the transition and then redirects the user to the Home page.
     */
    @Step("Logging out from Admin")
    public HomePage logout() {
        navbar.clickLogoutBtn();  // Click the logout button in the AdminNavbar

        // Wait for the HomePage to load after logout
        HomePage homePage = new HomePage();
        homePage.waitForPageToLoad();
        return homePage;
    }
}

