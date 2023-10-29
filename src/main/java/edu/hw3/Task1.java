package edu.hw3;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task1 {
    private Task1() {
    }

    @NotNull
    public static String atbash(String string) {
        Objects.requireNonNull(string);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); ++i) {
            stringBuilder.append(encryptSymbol(string.charAt(i)));
        }

        return stringBuilder.toString();
    }

    private static boolean isLowerLatinLetter(char symbol) {
        return (symbol >= 'a' && symbol <= 'z');
    }

    private static boolean isUpperLatinLetter(char symbol) {
        return (symbol >= 'A' && symbol <= 'Z');
    }

    private static char encryptSymbol(char symbol) {
        if (isLowerLatinLetter(symbol)) {
            return (char) ('a' + 'z' - symbol);
        }

        if (isUpperLatinLetter(symbol)) {
            return (char) ('A' + 'Z' - symbol);
        }

        return symbol;
    }
}

