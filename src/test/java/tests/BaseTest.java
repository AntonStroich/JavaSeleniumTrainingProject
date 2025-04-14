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

public class BaseTest {
    // Protected so subclasses (test classes) can access the WebDriver instance
    protected WebDriver driver;
    protected WebDriverWait wait; // shared wait instance for all test methods
    protected Timeouts timeout = Timeouts.MEDIUM; // Default timeout set to MEDIUM (10 seconds)

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

        // Use system property if set, otherwise fallback to parameter from XML,
        // and if neither is present, default to "chrome".
        String browser = System.getProperty("browser", browserFromXML != null ? browserFromXML : "chrome");

        // Convert browser name to lowercase for case-insensitive comparison
        switch(browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                // SafariDriver is only available on macOS systems
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    driver = new SafariDriver();
                } else {
                    throw new UnsupportedOperationException("Safari is only supported on macOS.");
                }
                break;
            default:
                // If the provided browser is not supported, throw an error
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Create an explicit wait using the timeout value from the Timeouts enum
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
    }

    @AfterClass
    public void tearDown() {
        if (driver !=null) {
            driver.quit(); // Closes all browser windows and safely ends the session
        }
    }

    /**
     * Method to dynamically change the timeout for WebDriverWait during test execution.
     *
     * @param timeout The new timeout value from the Timeouts enum.
     */
    public void setTimeout(Timeouts timeout) {
        this.timeout = timeout;
        // Update the WebDriverWait with the new timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout.getSeconds()));
    }
}
