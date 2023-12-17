package edu.hw5.Task7;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task7 {
    private static final String REGEX_SUBTASK1 = "^[01]{2}0([01]*)$";
    private static final Pattern PATTERN_SUBTASK1 = Pattern.compile(REGEX_SUBTASK1);
    private static final String REGEX_SUBTASK2 = "^([01])[01]*\\1|[01]$";
    private static final Pattern PATTERN_SUBTASK2 = Pattern.compile(REGEX_SUBTASK2);
    private static final String REGEX_SUBTASK3 = "^[01]{1,3}$";
    private static final Pattern PATTERN_SUBTASK3 = Pattern.compile(REGEX_SUBTASK3);

    private Task7() {
    }

    public static boolean isSatisfyFirstCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN_SUBTASK1.matcher(binaryString).matches();
    }

    public static boolean isSatisfySecondCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN_SUBTASK2.matcher(binaryString).matches();
    }

    public static boolean isSatisfyThirdCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN_SUBTASK3.matcher(binaryString).matches();
    }
}
