package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int RADIX = 10;

    private Task2() {
    }

    public static int countDigits(int decimalNumber) {
        LOGGER.trace("Counting the number of digits {}", decimalNumber);

        int counterDigits = 1;

        int absoluteDecimalNumber = Math.abs(decimalNumber);
        while (absoluteDecimalNumber > RADIX - 1) {
            absoluteDecimalNumber /= RADIX;
            ++counterDigits;
        }

        return counterDigits;
    }
}
