package pages;

import elements.Button;
import elements.TextField;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

/**
 * Page Object representing the Admin Login Page.
 */
public class AdminLoginPage extends AdminBasePage {

    // Locators
    private final By loginCard = By.className("card");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("doLogin");

    public AdminLoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Getters for wrapped elements
    private TextField getUsernameInput() {
        return new TextField(driver, driver.findElement(usernameField));
    }

    private TextField getPasswordInput() {
        return new TextField(driver, driver.findElement(passwordField));
    }

    private Button getLoginBtn() {
        return new Button(driver, driver.findElement(loginBtn));
    }

    /**
     * Opens the Admin Login Page using config value.
     */
    @Step("Open Admin Login Page using 'baseUrlAdmin'")
    public void openPage() {
        logger.info("Opening Admin Login Page");
        openUrl("baseUrlAdmin");
        waitForPageToLoad();
    }

    /**
     * Waits for the login form to be visible.
     */
    @Step("Wait for Admin Login Page to load")
    @Override
    public void waitForPageToLoad() {
        logger.info("Waiting for login card to be visible on Admin Login Page");
        waitForPageToLoad(loginCard);
    }

    /**
     * Enters username into the username field.
     */
    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        getUsernameInput().clearAndType(username);
    }

    /**
     * Enters password into the password field.
     */
    @Step("Enter password")
    public void enterPassword(String password) {
        logger.info("Entering password");
        getPasswordInput().clearAndType(password);
    }

    /**
     * Clicks the login button.
     */
    @Step("Click login button")
    public void clickLoginBtn() {
        logger.info("Clicking Login button");
        getLoginBtn().click();
    }

    /**
     * Logs in using the admin credentials stored in the local configuration.
     */
    @Step("Login to Admin using valid credentials from config")
    public AdminRoomsPage loginWithValidAdminCredentialsFromConfig() {
        String username = ConfigReader.getLocalProperty("username");
        String password = ConfigReader.getLocalProperty("password");
        loginAs(username, password);

        // Wait for the transition to AdminRoomsPage
        AdminRoomsPage adminRoomsPage = new AdminRoomsPage(driver, wait);
        adminRoomsPage.waitForPageToLoad();
        return adminRoomsPage;
    }

    /**
     * Completes the login process by entering the provided username and password.
     */
    @Step("Login to Admin as user: {username}")
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
    }
}