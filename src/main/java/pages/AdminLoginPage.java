package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

/**
 * Page Object representing the Admin Login Page.
 * Encapsulates interactions with the login form.
 */
public class AdminLoginPage extends BasePage {

    // Locators for login form elements
    private final By loginCard = By.className("card");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.cssSelector(".btn.btn-primary");

    public AdminLoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Getters for WebElements
    private WebElement getLoginCard() {
        return driver.findElement(loginCard);
    }

    private WebElement getUsernameField() {
        return driver.findElement(usernameField);
    }

    private WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }

    private WebElement getLoginBtn() {
        return driver.findElement(loginBtn);
    }

    /**
     * Enters the username in the input field.
     */
    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        clearAndType(getUsernameField(), username);
    }

    /**
     * Logs in to the Admin Panel using credentials stored in the local configuration file.
     *
     * This method reads the username and password from properties defined in `local.config`,
     * and performs the login flow using {@link #loginAs(String, String)}.
     *
     * Useful for avoiding hardcoded credentials in tests and maintaining cleaner code.
     */
    @Step("Login to Admin using credentials from config")
    public void loginAsAdminFromConfig() {
        String username = ConfigReader.getLocalProperty("username");
        String password = ConfigReader.getLocalProperty("password");
        loginAs(username, password);
    }

    /**
     * Enters the password in the input field.
     */
    @Step("Enter password")
    public void enterPassword(String password) {
        logger.info("Entering password");
        clearAndType(getPasswordField(), password);
    }

    /**
     * Clicks on the login button.
     */
    @Step("Click on Login button")
    public void clickLogin() {
        logger.info("Clicking login button");
        clickElement(getLoginBtn());
    }

    /**
     * Opens the admin login page using URL from config.
     */
    @Step("Open Admin Login Page using URL from config")
    public void openPage() {
        openUrl("baseUrlAdmin");
        waitForPageToLoad();
    }

    /**
     * Waits for the login form to be visible.
     */
    @Step("Wait for Admin Login Page to load")
    @Override
    public void waitForPageToLoad() {
        logger.info("Waiting for Admin Login Page to load");
        waitForPageToLoad(loginCard);
    }

    /**
     * Full login scenario with username and password input followed by login action.
     * Navigates to AdminRoomsPage after successful login.
     */
    @Step("Login to Admin as user: {username}")
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();

        // Transition to next page after successful login
        AdminRoomsPage adminRoomsPage = new AdminRoomsPage(driver, wait);
        adminRoomsPage.waitForPageToLoad();
    }
}

