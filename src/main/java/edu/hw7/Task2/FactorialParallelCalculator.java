package edu.hw7.Task2;

import java.util.stream.LongStream;

public final class FactorialParallelCalculator {
    private static final int MAX_ALLOWED_NUMBER = 20;

    private FactorialParallelCalculator() {
    }

    public static long calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Factorial is undefined for number %d".formatted(number));
        }

        // 21! > 2 ^ 63 - 1
        if (number > MAX_ALLOWED_NUMBER) {
            throw new IllegalArgumentException("It is impossible to represent the result "
                + "of factorial calculation in the long type for the number %d".formatted(number));
        }

        return LongStream
            .rangeClosed(1, number)
            .parallel()
            .reduce(1, (long a, long b) -> a * b);
    }
}
