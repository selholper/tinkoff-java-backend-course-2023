package edu.hw1;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String videoLength) {
        Objects.requireNonNull(videoLength);
        LOGGER.trace("Processing the string {}", videoLength);
        Pattern regex = Pattern.compile("^(\\d\\d+):([0-5][0-9])$");
        Matcher matcher = regex.matcher(videoLength);

        if (!matcher.matches()) {
            return -1;
        }

        int minutes = Integer.parseInt(matcher.group(1));

        return SECONDS_PER_MINUTE * minutes + Integer.parseInt(matcher.group(2));
    }
}
