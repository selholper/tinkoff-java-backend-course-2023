package edu.hw5.Task5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task5 {
    private static final String REGEX = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2}(?<!00)\\d?$";
    private static final Pattern CAR_NUMBER_PATTERN = Pattern.compile(REGEX);

    private Task5() {
    }

    public static boolean validateCarNumber(String carNumber) {
        Objects.requireNonNull(carNumber);

        return CAR_NUMBER_PATTERN.matcher(carNumber).matches();
    }
}
