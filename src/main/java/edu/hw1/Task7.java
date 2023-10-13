package edu.hw1;

import java.util.ArrayDeque;
import java.util.Deque;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int RADIX = 2;

    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        LOGGER.trace("Cyclically shift the number {} by {} positions to the left", n, shift);

        if (n < 1 || shift < 0) {
            throw new IllegalArgumentException("The number must be positive and"
                + " the shift must not be negative for the rotateLeft operation");
        }

        Deque<Boolean> binNumber = new ArrayDeque<>();
        int number = n;

        while (number > 0) {
            binNumber.addFirst(number % RADIX == 1);
            number /= RADIX;
        }
        for (int i = 0; i < shift; ++i) {
            boolean rank = binNumber.removeFirst();
            binNumber.addLast(rank);
        }

        int result = 0;
        int base = 1;
        while (!binNumber.isEmpty()) {
            if (binNumber.removeLast()) {
                result += base;
            }
            base *= RADIX;
        }

        return result;
    }

    public static int rotateRight(int n, int shift) {
        if (n < 1 || shift < 0) {
            throw new IllegalArgumentException("The number must be positive and "
                + "the shift must not be negative for the rotateRight operation");
        }

        Deque<Boolean> binNumber = new ArrayDeque<>();
        int number = n;

        while (number > 0) {
            binNumber.addFirst(number % RADIX == 1);
            number /= RADIX;
        }
        for (int i = 0; i < shift; ++i) {
            boolean rank = binNumber.removeLast();
            binNumber.addFirst(rank);
        }

        int result = 0;
        int base = 1;
        while (!binNumber.isEmpty()) {
            if (binNumber.removeLast()) {
                result += base;
            }
            base *= RADIX;
        }

        return result;
    }
}
