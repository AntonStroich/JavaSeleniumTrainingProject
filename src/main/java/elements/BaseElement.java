package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class for all custom UI elements.
 * Wraps a Selenium WebElement and provides common interaction methods with logging.
 */
public  class BaseElement {
    protected WebDriver driver;
    protected WebElement element;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructor for wrapping a WebElement.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to wrap
     */
    public BaseElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    /**
     * Checks if the element is visible on the page.
     */
    public boolean isDisplayed() {
        boolean displayed = element.isDisplayed();
        logger.debug("Element isDisplayed: {}", displayed);
        return displayed;
    }

    /**
     * Checks if the element is enabled and interactable.
     */
    public boolean isEnabled() {
        boolean enabled = element.isEnabled();
        logger.debug("Element isEnabled: {}", enabled);
        return enabled;
    }

    /**
     * Clicks on the element.
     */
    public void click() {
        logger.info("Clicking on element: {}", describeElement());
        element.click();
    }

    /**
     * Retrieves the visible text of the element.
     */
    public String getText() {
        String text = element.getText();
        logger.info("Getting text from element: {}, text: {}", describeElement(), text);
        return text;
    }

    /**
     * Helper method to provide a description of the element for logging purposes.
     */
    protected String describeElement() {
        try {
            return element.toString();
        } catch (Exception e) {
            return "unknown element";
        }
    }
}
