package edu.hw5.Task4;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task4 {
    private static final String REGEX = ".*[~!@#$%^&*|].*";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(REGEX);

    private Task4() {
    }

    public static boolean isValidPassword(String password) {
        Objects.requireNonNull(password);

        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
