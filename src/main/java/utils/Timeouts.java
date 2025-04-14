package utils;

public enum Timeouts {
    // Enum constants representing different timeout durations
    SHORT(5),  // 5 seconds timeout
    MEDIUM(10), // 10 seconds timeout
    LONG(30);   // 30 seconds timeout

    // Instance variable to hold the value of the timeout in seconds
    private final int seconds;

    /**
     * Constructor to initialize the timeout value.
     *
     * @param seconds The timeout duration in seconds.
     */
    Timeouts(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Method to retrieve the timeout duration in seconds.
     *
     * @return The timeout duration in seconds.
     */
    public int getSeconds() {
        return seconds;
    }
}
