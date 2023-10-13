package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw1.Task2.countDigits;

public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NUMBER_OF_DIGITS = 4;
    private static final int RADIX = 10;
    private static final int TASK_CONSTANT = 6174;
    private static final int LOWER_BOUND_FOUR_DIGITS_NUMBER = 1000;

    private Task6() {
    }

    private static boolean checkIfDigitsEqual(int[] digits) {
        for (int i = 0; i < NUMBER_OF_DIGITS - 1; ++i) {
            if (digits[i] != digits[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private static void sortNumberDigits(int[] digits) {
        for (int i = 0; i < NUMBER_OF_DIGITS - 1; ++i) {
            for (int j = i + 1; j < NUMBER_OF_DIGITS; ++j) {
                if (digits[i] > digits[j]) {
                    int interim = digits[i];
                    digits[i] = digits[j];
                    digits[j] = interim;
                }
            }
        }
    }

    private static int getNextNumber(int[] digits) {
        int numberWithAscendingDigits = 0;
        int numberWithDescendingDigits = 0;
        int nextNumber;

        for (int i = 0; i < NUMBER_OF_DIGITS; ++i) {
            numberWithAscendingDigits *= RADIX;
            numberWithAscendingDigits += digits[i];
            numberWithDescendingDigits *= RADIX;
            numberWithDescendingDigits += digits[NUMBER_OF_DIGITS - i - 1];
        }

        nextNumber = numberWithDescendingDigits - numberWithAscendingDigits;
        if (nextNumber < LOWER_BOUND_FOUR_DIGITS_NUMBER) {
            nextNumber *= RADIX;
        }
        return nextNumber;
    }

    public static int countK(int number) {
        if (countDigits(number) != NUMBER_OF_DIGITS || number == LOWER_BOUND_FOUR_DIGITS_NUMBER) {
            throw new IllegalArgumentException("The number must be four digits and not equal to 1000");
        }

        int copyNumber = number;
        int[] digits = new int[NUMBER_OF_DIGITS];
        for (int i = 0; i < NUMBER_OF_DIGITS; ++i) {
            digits[i] = copyNumber % RADIX;
            copyNumber /= RADIX;
        }

        if (!checkIfDigitsEqual(digits)) {
            throw new IllegalArgumentException("All digits of the number must not be the same");
        }

        LOGGER.trace("Processing the number {}", number);

        if (number == TASK_CONSTANT) {
            return 0;
        }

        sortNumberDigits(digits);
        int nextNumber = getNextNumber(digits);

        return countK(nextNumber) + 1;
    }
}
