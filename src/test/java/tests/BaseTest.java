package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.Timeouts;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    // Protected so subclasses (test classes) can access the WebDriver instance
    protected WebDriver driver;
    // shared wait instance for all test methods
    protected WebDriverWait wait;
    // Default timeout set to MEDIUM (10 seconds)
    protected Timeouts timeout = Timeouts.MEDIUM;
    // Initialize the logger for this class using SLF4J and Logback
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    /**
     * This method runs once before any test methods in the class are executed.
     * It initializes the WebDriver instance based on the browser name provided
     * via TestNG's XML file or from the system property.
     *
     * @param browserFromXML The browser name passed from testng.xml as a parameter.
     *                       If not provided, the method will fall back to a default.
     */
    @BeforeClass
    @Parameters("browser")
    // If no 'browser' parameter is provided (e.g., when running without testng.xml),
    // the default browser "chrome" will be used.
    public void setUp(@Optional String browserFromXML) {
        // Log the start of the setup
        logger.info("Starting setup for browser: {}", browserFromXML);
        // Use system property if set, otherwise fallback to parameter from XML,
        // and if neither is present, default to "chrome".
        String browser = System.getProperty("browser", browserFromXML != null ? browserFromXML : "chrome");
        // Log the selected browser
        logger.info("Selected browser: {}", browser);

        // Convert browser name to lowercase for case-insensitive comparison
        switch(browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("ChromeDriver setup completed.");
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                logger.info("EdgeDriver setup completed.");
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                logger.info("Firefox setup completed.");
                break;
            case "safari":
                // SafariDriver is only available on macOS systems
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    logger.info("Safari setup completed.");
                    driver = new SafariDriver();
                } else {
                    throw new UnsupportedOperationException("Safari is only supported on macOS.");
                }
                break;
            default:
                // If the provided browser is not supported, throw an error
                logger.error("Unsupported browser: {}", browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Create an explicit wait using the timeout value from the Timeouts enum
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
        logger.info("WebDriverWait initialized with timeout: {} seconds", timeout.getSeconds());
    }

    @AfterClass
    public void tearDown() {
        if (driver !=null) {
            driver.quit(); // Closes all browser windows and safely ends the session
            logger.info("WebDriver session closed.");
        }
    }

    /**
     * Method to dynamically change the timeout for WebDriverWait during test execution.
     *
     * @param timeout The new timeout value from the Timeouts enum.
     */
    public void setTimeout(Timeouts timeout) {
        this.timeout = timeout;
        // Log the timeout change
        logger.info("Timeout changed to: {} seconds", timeout.getSeconds());
        // Update the WebDriverWait with the new timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
    }
}
