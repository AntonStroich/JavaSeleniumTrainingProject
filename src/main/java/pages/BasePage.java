package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Opens the page. This method must be implemented in subclasses.
     */
    @Step("Open page")
    public abstract void open();

    /**
     * Waits for the page to fully load.
     */
    @Step("Wait for page to load")
    protected void waitForPageToLoad(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    };

    /**
     * Returns the URL for the specific page.
     */
    protected String getUrl(String url) {
        return ConfigReader.getConfigProperty(url);
    };

    /**
     * Retrieves the current page title.
     * This method is commonly used in tests for validation purposes,
     * and can be reused across all page classes that extend BasePage.
     *
     * @return The title of the currently loaded page.
     */
    @Step("Getting the current page title")
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Current page title: {}", title);
        return title;
    }
}

