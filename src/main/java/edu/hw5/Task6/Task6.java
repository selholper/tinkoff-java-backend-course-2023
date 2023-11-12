package edu.hw5.Task6;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task6 {
    private Task6() {
    }

    public static boolean isOneStringSubsequenceOfAnother(String s, String t) {
        Objects.requireNonNull(s);
        Objects.requireNonNull(t);

        final String regex = "^.*" + s + ".*$";
        final Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(t).matches();
    }
}
