package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final By logo = By.className("hotel-logoUrl");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Open Home Page using URL from config")
    @Override
    public void open() {
        logger.info("Opening Home Page using URL from config");
        openUrl("baseUrl");
        waitForPageToLoad();
    }

    @Step("Wait for Home Page to be loaded")
    @Override
    public void waitForPageToLoad() {
        logger.info("Wait for Home Page to be loaded");
        waitForPageToLoad(logo);
    }

}
