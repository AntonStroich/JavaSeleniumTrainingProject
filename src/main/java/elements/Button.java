package elements;

import org.openqa.selenium.WebElement;

/**
 * Custom Button element with additional actions.
 */
public class Button extends BaseElement {

    /**
     * Constructor for a button element.
     *
     * @param element WebElement representing the button
     */
    public Button(WebElement element) {
        super(element);
    }
}
