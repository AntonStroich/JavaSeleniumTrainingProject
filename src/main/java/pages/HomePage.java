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

    @Step("Opening Home Page using URL from config")
    @Override
    public void open() {
        String url = getUrl("baseUrl");
        logger.info("Opening Home Page at URL: {}", url);
        driver.get(url);
        waitForPageToLoad(logo);
    }

}
