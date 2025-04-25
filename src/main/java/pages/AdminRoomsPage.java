package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * Page Object for the Admin Rooms management page.
 */
public class AdminRoomsPage extends AdminBasePage {

    private final By createRoomBtn = By.id("createRoom");

    public AdminRoomsPage() {
        super();
    }

    /**
     * Waits for the Admin Rooms Page to fully load.
     */
    @Step("Wait for Admin Rooms Page to load")
    @Override
    public void waitForPageToLoad() {
        logger.info("Waiting for 'Create Room' button to be visible on Admin Rooms Page");
        waitForPageToLoad(createRoomBtn);
    }
}