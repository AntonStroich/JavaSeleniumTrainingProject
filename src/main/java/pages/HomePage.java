package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object for the Home Page.
 * Contains logic to open and wait for the main site page.
 */
public class HomePage extends BasePage {

    private final By logo = By.cssSelector(".btn.btn-primary.btn-lg");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Opens the home page using the base URL from config.
     */
    @Step("Open Home Page using 'baseUrl' config")
    public void open() {
        logger.info("Opening Home Page");
        openUrl("baseUrl");
        waitForPageToLoad();
    }

    /**
     * Waits for the Home Page to be fully loaded.
     */
    @Step("Wait for Home Page to load")
    @Override
    public void waitForPageToLoad() {
        logger.info("Waiting for Home Page logo to be visible");
        waitForPageToLoad(logo);
    }
}