package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
     * Waits for the page to fully load.
     */
    @Step("Wait for page to load")
    protected void waitForPageToLoad(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    };

    /**
     * Returns the URL for the specific page.
     */
    private String getUrl(String url) {
        return ConfigReader.getConfigProperty(url);
    };

    @Step("Open Url")
    protected void openUrl(String url) {
        String urlToOpen = getUrl(url);
        logger.info("Opening URL: {}", url);
        driver.get(urlToOpen);
    };

    /**
     * Types text into a WebElement after clearing it.
     */
    @Step("Clear and type text into element")
    protected void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clicks on the given WebElement.
     */
    @Step("Click on element")
    protected void clickElement(WebElement element) {
        element.click();
    }

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

    /**
     * Abstract method that must be implemented by each page object
     * to define the specific condition that determines when the page is fully loaded.
     * Typically used to wait for a key element to become visible.
     */
    public abstract void waitForPageToLoad();
}

