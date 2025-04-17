package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminRoomsPage extends BasePage {

    private final By createRoomBtn = By.id("createRoom");

    public AdminRoomsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Getters for WebElements
    private WebElement getCreateRoomBtn() {
        return driver.findElement(createRoomBtn);
    }

    @Step("Wait for Admin Rooms Page to be loaded")
    @Override
    public void waitForPageToLoad() {
        logger.info("Wait for Admin Rooms Page to be loaded");
        waitForPageToLoad(createRoomBtn);
    }
}
