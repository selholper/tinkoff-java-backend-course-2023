package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AfterDateParser implements DateParser {
    private static final String REGEX = "^(\\d{1,4}) days? after$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public Optional<LocalDate> parse(String string) {
        Matcher matcher = PATTERN.matcher(string);

        if (matcher.matches()) {
            int days = Integer.parseInt(matcher.group(1));
            return Optional.of(LocalDate.now().plusDays(days));
        } else {
            return Optional.empty();
        }
    }
}
