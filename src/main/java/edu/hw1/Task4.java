package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task4() {
    }

    public static String fixString(String brokenString) {
        Objects.requireNonNull(brokenString);
        LOGGER.trace("Fixing a broken string {}", brokenString);

        StringBuilder correctedString = new StringBuilder(brokenString);

        for (int i = 0; i < correctedString.length() / 2; ++i) {
            char curChar = correctedString.charAt(2 * i);
            char nextChar = correctedString.charAt(2 * i + 1);
            correctedString.setCharAt(2 * i, nextChar);
            correctedString.setCharAt(2 * i + 1, curChar);
        }

        return correctedString.toString();
    }
}
