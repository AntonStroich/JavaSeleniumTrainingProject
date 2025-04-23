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

public class BaseTest {
    protected Timeouts timeout = Timeouts.MEDIUM;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional String browserFromXML) {
        String browser = System.getProperty("browser", browserFromXML != null ? browserFromXML : "chrome");
        logger.info("Starting setup for browser: {}", browser);

        WebDriver driver = createDriver(browser.toLowerCase());
        DriverManager.setDriver(driver, timeout);
        logger.info("Driver initialized and set into DriverManager.");
    }

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

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Driver quit via DriverManager.");
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected WebDriverWait getWait() {
        return DriverManager.getWait();
    }

    public void setTimeout(Timeouts timeout) {
        this.timeout = timeout;
        DriverManager.setDriver(getDriver(), timeout);
    }
}
