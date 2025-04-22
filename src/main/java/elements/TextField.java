package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Custom TextField element with separate methods for typing and clearing.
 */
public class TextField extends BaseElement {

    /**
     * Constructor for a text input field.
     *
     * @param driver  WebDriver instance
     * @param element WebElement representing the text field
     */
    public TextField(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    /**
     * Types the provided text into the field without clearing it first.
     *
     * @param text Text to input
     */
    public void type(String text) {
        logger.info("Typing text into field: '{}', element: {}", text, describeElement());
        element.sendKeys(text);
    }

    /**
     * Clears the current value of the text field.
     */
    public void clear() {
        logger.info("Clearing text field: {}", describeElement());
        element.clear();
    }

    /**
     * Clears the field and then types the provided text.
     *
     * @param text Text to input after clearing the field
     */
    public void clearAndType(String text) {
        logger.info("Clearing field before typing: '{}', element: {}", text, describeElement());
        clear();
        type(text);
    }
}