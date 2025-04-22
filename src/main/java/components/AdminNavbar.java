package components;

import elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.AdminRoomsPage;
import utils.ConfigReader;

/**
 * Represents the Admin Navbar component.
 * Inherits from BaseComponent and provides specific implementation for Admin Navbar.
 */
public class AdminNavbar extends BaseComponent {

    // Locator for the Admin Navbar component
    private final By navbar = By.id("admin-navbar");
    // Locator for the "Logout" button
    private final By logoutBtn = By.cssSelector(".btn.btn-outline-danger.my-2.my-sm-0");

    public AdminNavbar(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns the locator for the Admin Navbar component.
     *
     * @return The locator for the Admin Navbar.
     */
    @Override
    protected By getRootLocator() {
        return navbar; // Return the locator for Admin Navbar
    }

    private Button getLogoutBtn() {
        return new Button(driver, driver.findElement(logoutBtn));
    }

    /**
     * Clicks the logout button.
     */
    @Step("Click logout button")
    public void clickLogoutBtn() {
        logger.info("Clicking Logout button");

        // Wait for transition to HomePage
        getLogoutBtn().click();
    }
}
