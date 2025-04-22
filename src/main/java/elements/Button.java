package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Custom Button element with additional actions.
 */
public class Button extends BaseElement {

    /**
     * Constructor for a button element.
     *
     * @param driver  WebDriver instance
     * @param element WebElement representing the button
     */
    public Button(WebDriver driver, WebElement element) {
        super(driver, element);
    }
}
