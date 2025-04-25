package components;

import elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * Represents the Admin Navbar component.
 * Inherits from BaseComponent and provides specific implementation for Admin Navbar.
 */
public class AdminNavbar extends BaseComponent {

    // Locator for the Admin Navbar component
    private final By navbar = By.cssSelector(".navbar.navbar-expand-md.navbar-dark.bg-dark.mb-4");
    // Locator for the "Logout" button
    private final By logoutBtn = By.cssSelector(".btn.btn-outline-danger.my-2.my-sm-0");

    public AdminNavbar() {
        super();
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
        return new Button(driver.findElement(logoutBtn));
    }

    /**
     * Clicks the logout button.
     */
    @Step("Click logout button")
    public void clickLogoutBtn() {
        logger.info("Clicking Logout button");
        getLogoutBtn().click();
    }
}
