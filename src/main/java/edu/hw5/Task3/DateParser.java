package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

interface DateParser {
    Optional<LocalDate> parse(String input);
}

