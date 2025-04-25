package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
import utils.DriverManager;

/**
 * Abstract base class for all page objects.
 * Provides shared functionality like page load waiting and URL opening.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage() {
        this.driver = DriverManager.getDriver();;
        this.wait = DriverManager.getWait();
    }

    /**
     * Abstract method to be implemented in child classes to define page-specific loading conditions.
     */
    public abstract void waitForPageToLoad();

    /**
     * Waits for the provided element locator to be visible on the page.
     *
     * @param element Locator of the element that signifies the page is loaded.
     */
    @Step("Wait for page to load: {element}")
    protected void waitForPageToLoad(By element) {
        logger.info("Waiting for visibility of element: {}", element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /**
     * Reads the actual URL from configuration file using its key.
     *
     * @param urlKey Property key for the URL
     * @return Resolved URL from configuration
     */
    private String getUrl(String urlKey) {
        return ConfigReader.getConfigProperty(urlKey);
    }

    /**
     * Opens the page using the URL resolved by key from configuration.
     *
     * @param urlKey Key in configuration for the target URL
     */
    @Step("Open URL by config key: {urlKey}")
    protected void openUrl(String urlKey) {
        String urlToOpen = getUrl(urlKey);
        logger.info("Opening URL: {}", urlToOpen);
        driver.get(urlToOpen);
    }

    /**
     * Retrieves the current browser page title.
     *
     * @return Current page title
     */
    @Step("Get current page title")
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Current page title: {}", title);
        return title;
    }
}
