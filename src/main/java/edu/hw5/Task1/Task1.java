package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class Task1 {
    private static final String REGEX = "^(.*) - (.*)$";
    private static final String RUSSIAN_DATE_FORMAT = "uuuu-MM-dd, HH:mm";
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile(REGEX);
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern(RUSSIAN_DATE_FORMAT);
    private static final int MINUTES_IN_HOUR = 60;

    private Task1() {
    }

    @NotNull
    public static String getAverageTimeOfVisitorsStay(List<String> listSession) {
        Objects.requireNonNull(listSession);

        if (listSession.isEmpty()) {
            throw new IllegalArgumentException("Average time is not defined for empty list");
        }

        long totalSessionTimeInMinutes = 0;
        for (String session : listSession) {
            Objects.requireNonNull(session);

            Matcher matcher = DATE_FORMAT_PATTERN.matcher(session);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Wrong string format");
            }

            String sessionStartTime = matcher.group(1);
            String sessionEndTime = matcher.group(2);
            LocalDateTime parsedSessionStartTime = LocalDateTime.parse(sessionStartTime, DATE_TIME_FORMATTER);
            LocalDateTime parsedSessionEndTime = LocalDateTime.parse(sessionEndTime, DATE_TIME_FORMATTER);
            Duration sessionTime = Duration.between(parsedSessionStartTime, parsedSessionEndTime);

            if (sessionTime.isNegative()) {
                throw new IllegalArgumentException("Session start time cannot be later than end time");
            }

            totalSessionTimeInMinutes += sessionTime.toMinutes();
        }
        long averageSessionTimeInMinutes = totalSessionTimeInMinutes / listSession.size();

        return averageSessionTimeInMinutes / MINUTES_IN_HOUR + "ч "
            + averageSessionTimeInMinutes % MINUTES_IN_HOUR + "м";
    }
}
