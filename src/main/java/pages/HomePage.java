package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

public class HomePage extends BasePage {

    private final By logo = By.className("hotel-logoUrl");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    protected String getUrl() {
        // Get the base URL directly from the config file
        return ConfigReader.getConfigProperty("baseUrl");
    }

    @Override
    @Step("Waiting for Home Page to load")
    protected void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
    }

    @Step("Opening Home Page using URL from config")
    @Override
    public void open() {
        String url = getUrl();
        logger.info("Opening Home Page at URL: {}", url);
        driver.get(url);
        waitForPageToLoad();
    }

    @Step("Getting the current page title")
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Current page title: {}", title);
        return title;
    }
}
