package utils;


// Importing necessary classes for file input and handling exceptions
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // Properties object that will hold all the configuration key-value pairs from the 'local.properties' file
    private static Properties properties = new Properties();

    // Static block that runs when the class is first loaded. It attempts to load the properties from the file.
    static {
        try {
            // Opening the 'local.properties' file using FileInputStream
            FileInputStream file = new FileInputStream("local.properties");
            // Loading the properties from the file into the 'properties' object
            properties.load(file);
        } catch (IOException error) {
            // If there's an IOException (e.g., file not found), throw a RuntimeException and stop the program
            throw new RuntimeException("Failed to load configuration from local.properties ", error);
        }
    }

    /**
     * This method retrieves the value associated with a given key from the loaded properties.
     *
     * @param key - The key whose value you want to retrieve from the properties file.
     * @return The value associated with the key as a String.
     */

    public static String getProperty(String key) {
        // Fetching the value for the specified key
        return properties.getProperty(key);
    }
}
