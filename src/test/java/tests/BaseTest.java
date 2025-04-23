package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.DriverManager;
import utils.Timeouts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseTest serves as the foundation for all test classes.
 * It handles WebDriver initialization, setup, teardown, and thread-local access to WebDriver and WebDriverWait.
 * Supports multi-browser testing and parallel test execution using DriverManager and ThreadLocal.
 */
public class BaseTest {

    // Default timeout setting (used for WebDriverWait)
    protected Timeouts timeout = Timeouts.MEDIUM;

    // SLF4J Logger for logging test lifecycle events
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    /**
     * This method is executed once before any test methods in the class.
     * It initializes the WebDriver for the current thread and registers it with DriverManager.
     *
     * @param browserFromXML Optional parameter passed from TestNG XML suite to specify browser type.
     */
    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional String browserFromXML) {
        // Priority: system property > XML parameter > default "chrome"
        String browser = System.getProperty("browser", browserFromXML != null ? browserFromXML : "chrome");
        logger.info("Starting setup for browser: {}", browser);

        // Create the browser-specific WebDriver
        WebDriver driver = createDriver(browser.toLowerCase());

        // Store the driver and wait into ThreadLocal via DriverManager
        DriverManager.setDriver(driver, timeout);
        logger.info("Driver initialized and set into DriverManager.");
    }

    /**
     * Factory method to create a WebDriver instance based on the provided browser name.
     * Uses WebDriverManager to handle driver binaries automatically.
     *
     * @param browser The name of the browser (e.g., "chrome", "firefox").
     * @return A new WebDriver instance.
     */
    private WebDriver createDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "safari":
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    return new SafariDriver();
                } else {
                    throw new UnsupportedOperationException("Safari is only supported on macOS.");
                }
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    /**
     * This method is executed once after all test methods in the class have run.
     * It ensures that the WebDriver is properly quit and removed from ThreadLocal storage.
     */
    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Driver quit via DriverManager.");
    }

    /**
     * Provides thread-safe access to the WebDriver instance for use in test methods.
     *
     * @return WebDriver instance tied to the current thread.
     */
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    /**
     * Provides thread-safe access to the WebDriverWait instance for use in test methods.
     *
     * @return WebDriverWait instance tied to the current thread.
     */
    protected WebDriverWait getWait() {
        return DriverManager.getWait();
    }

    /**
     * Dynamically updates the timeout value used for WebDriverWait.
     * Useful if different tests require different wait durations.
     *
     * @param timeout The new timeout value (from Timeouts enum).
     */
    public void setTimeout(Timeouts timeout) {
        this.timeout = timeout;
        DriverManager.setDriver(getDriver(), timeout); // Reapply the driver with new timeout
    }
}
