package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    private static final List<DateParser> DATE_PARSER = List.of(
        new FormatterDateParser(DateTimeFormatter.ofPattern("uuuu-M-d")),
        new FormatterDateParser(DateTimeFormatter.ofPattern("uu-M-d")),
        new FormatterDateParser(DateTimeFormatter.ofPattern("M/d/uuuu")),
        new FormatterDateParser(DateTimeFormatter.ofPattern("M/d/uu")),
        new FormatterDateParser(DateTimeFormatter.ofPattern("d/M/uuuu")),
        new FormatterDateParser(DateTimeFormatter.ofPattern("d/M/uu")),
        new WordDateParser(),
        new BeforeDateParser(),
        new AfterDateParser()
    );

    public static Optional<LocalDate> parseDate(String string) {
        Objects.requireNonNull(string);

        return DATE_PARSER
            .stream()
            .filter(parser -> parser.parse(string).isPresent())
            .findAny()
            .flatMap(any -> any.parse(string));
    }
}
