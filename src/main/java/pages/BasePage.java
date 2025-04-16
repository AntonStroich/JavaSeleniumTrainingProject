package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * Must be implemented by each specific page.
     */
    @Step("Wait for page to load")
    protected abstract void waitForPageToLoad();

    /**
     * Returns the URL for the specific page. This must be implemented in each subclass.
     */
    protected abstract String getUrl();
}

