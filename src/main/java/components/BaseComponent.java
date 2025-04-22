package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for reusable UI components (e.g., navbar, footer).
 * Contains methods to check if the component is displayed.
 */
public abstract class BaseComponent {
    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
    }

    // Abstract method for child classes to define the root element locator of the component
    protected abstract By getRootLocator();

    /**
     * Returns the root element of the component.
     *
     * @return the root WebElement of the component.
     */
    private WebElement getRootElement() {
        return driver.findElement(getRootLocator()); // Find the element using locator
    }

    /**
     * Checks if the component is visible on the page.
     */
    public boolean isDisplayed() {
        WebElement element = getRootElement(); // Use the getter method to access the root element
        boolean displayed = element.isDisplayed();
        logger.debug("Component with locator {} is displayed: {}", getRootLocator(), displayed);
        return displayed;
    }
}
