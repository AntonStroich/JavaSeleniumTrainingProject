package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * DriverManager is a utility class that manages WebDriver and WebDriverWait instances
 * using ThreadLocal to ensure thread safety for parallel test execution.
 * Each thread will get its own copy of WebDriver and WebDriverWait,
 * avoiding conflicts in a multithreaded (parallel) environment.
 */
public class DriverManager {

    // ThreadLocal to store WebDriver instance per thread
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // ThreadLocal to store WebDriverWait instance per thread
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    /**
     * Retrieves the WebDriver instance for the current thread.
     *
     * @return WebDriver instance specific to the calling thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Retrieves the WebDriverWait instance for the current thread.
     *
     * @return WebDriverWait instance specific to the calling thread
     */
    public static WebDriverWait getWait() {
        return wait.get();
    }

    /**
     * Sets the WebDriver and initializes WebDriverWait for the current thread.
     *
     * @param webDriver The WebDriver instance to associate with the thread
     * @param timeout   Enum value from Timeouts class that defines wait duration
     */
    public static void setDriver(WebDriver webDriver, Timeouts timeout) {
        driver.set(webDriver);
        wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(timeout.getSeconds())));
    }

    /**
     * Quits the WebDriver session and removes thread-local variables to avoid memory leaks.
     * Should be called in @AfterClass or @AfterMethod depending on test scope.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();   // Safely close browser and end session
            driver.remove();       // Remove WebDriver reference from current thread
            wait.remove();         // Remove WebDriverWait reference from current thread
        }
    }
}
