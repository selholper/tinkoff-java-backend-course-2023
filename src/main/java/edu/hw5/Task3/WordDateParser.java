package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateParser implements DateParser {
    @Override
    public Optional<LocalDate> parse(String string) {
        switch (string) {
            case "tomorrow" -> {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            case "today" -> {
                return Optional.of(LocalDate.now());
            }
            case "yesterday" -> {
                return Optional.of(LocalDate.now().minusDays(1));
            }
            default -> {
                return Optional.empty();
            }
        }
    }
}
