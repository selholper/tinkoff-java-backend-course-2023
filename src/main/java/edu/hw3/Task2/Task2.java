package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private Task2() {
    }

    private static boolean notCorrectBracketSequence(String s) {
        int openBracketsCounter = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++openBracketsCounter;
            } else if (s.charAt(i) == ')') {
                --openBracketsCounter;
            } else {
                return true;
            }

            if (openBracketsCounter < 0) {
                return true;
            }
        }

        return openBracketsCounter != 0;
    }

    @NotNull
    public static List<String> clusterize(String string) {
        Objects.requireNonNull(string);

        if (notCorrectBracketSequence(string)) {
            throw new IllegalArgumentException("String must be right parenthesis sequence");
        }

        int openBracketsCounter = 0;
        List<String> clusters = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); ++i) {
            stringBuilder.append(string.charAt(i));
            if (string.charAt(i) == '(') {
                ++openBracketsCounter;
            } else {
                --openBracketsCounter;
            }

            if (openBracketsCounter == 0) {
                clusters.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }

        return clusters;
    }
}

