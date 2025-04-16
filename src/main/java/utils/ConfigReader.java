package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConfigReader is a utility class for reading configuration properties
 * from two different sources:
 * 1. config.properties - contains non-sensitive project-wide settings (e.g., baseUrl).
 * 2. local.properties - contains sensitive or environment-specific data (e.g., credentials).
 *
 * This allows better separation of secrets from general settings and makes it easier
 * to manage configurations across different environments.
 */
public class ConfigReader {
    // Logger initialization for logging in this class
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    // Properties object to store public config (e.g., environment URLs)
    private static final Properties configProps = new Properties();

    // Properties object to store sensitive or machine-specific data (e.g., login, password)
    private static final Properties secretProps = new Properties();

    // Static block is executed once when the class is loaded
    static {
        try {
            // Load general configurations from src/main/resources/config.properties
            logger.info("Loading config.properties...");
            FileInputStream configFile = new FileInputStream("src/main/resources/config.properties");
            configProps.load(configFile);

            // Load secrets from local.properties (should be excluded from version control)
            logger.info("Loading local.properties...");
            FileInputStream secretFile = new FileInputStream("src/main/resources/local.properties");
            secretProps.load(secretFile);

        } catch (IOException e) {
            // If the file cannot be loaded, throw a runtime exception to prevent tests from running
            logger.error("Failed to load configuration files", e);
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }

    /**
     * Retrieves the value associated with the given key from the config.properties file,
     * which typically stores non-sensitive configuration such as base URLs, timeouts, flags, etc.
     *
     * If the key is not found, a RuntimeException will be thrown to ensure that required configuration
     * values are not missing at runtime.
     *
     * @param key The property key to retrieve from config.properties.
     * @return The corresponding value for the provided key.
     * @throws RuntimeException if the key is not found in the config.properties file.
     */

    public static String getConfigProperty(String key) {
        String value = configProps.getProperty(key);
        if (value == null) {
            logger.error("Missing required configuration property: {}", key);
            throw new RuntimeException("Missing required configuration property: " + key);
        }
        return value;
    }


    /**
     * Retrieves the value associated with the given key from the local.properties file,
     * which typically contains sensitive or environment-specific configuration such as credentials.
     *
     * If the key is not found, a RuntimeException is thrown to avoid silent failures.
     *
     * @param key The property key to retrieve from local.properties.
     * @return The corresponding value for the provided key.
     * @throws RuntimeException if the key does not exist in the properties file.
     */

    public static String getLocalProperty(String key) {
        String value = secretProps.getProperty(key);
        if (value == null) {
            logger.error("Missing required local property: {}", key);
            throw new RuntimeException("Missing required local property: " + key);
        }
        return value;
    }
}
