package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class FormatterDateParser implements DateParser {
    private final DateTimeFormatter formatter;

    public FormatterDateParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Optional<LocalDate> parse(String string) {
        try {
            LocalDate date = LocalDate.parse(string, formatter);
            return Optional.of(date);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
