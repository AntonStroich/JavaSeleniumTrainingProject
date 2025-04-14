package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;

public class Test01 extends BaseTest {

    // Test to open the URL from the config and verify the page title
    @Test
    public void openAndVerifyTitle() {
        // Get the base URL from local.properties using ConfigReader
        String baseUrl = ConfigReader.getProperty("baseUrl");

        // Get the base title of the page from local.properties using ConfigReader
        String expectedPageTitle = ConfigReader.getProperty("pageTitle");

        // Retrieve the class name from the local.properties file using ConfigReader
        String className = ConfigReader.getProperty("className");

        // Open the URL
        driver.get(baseUrl);

        // Wait dynamically for the element to become visible, with a timeout duration
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));

        // Get the title of the page
        String pageTitle = driver.getTitle();

        // Verify the title is correct (make sure the expected title is in the properties file or hardcoded)
        Assert.assertEquals(pageTitle, expectedPageTitle, "Page title does not match");
    }
}
