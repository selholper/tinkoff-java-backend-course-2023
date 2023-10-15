package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw1.Task2.countDigits;

public final class Task5 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int RADIX = 10;
    private static final int SQUARE_RADIX = RADIX * RADIX;

    private Task5() {
    }

    private static boolean isNumberCountDigitsOdd(int number) {
        return countDigits(number) % 2 == 1;
    }

    private static int reverseNumber(int number) {
        int reverseNumber = 0;
        int copyNumber = number;

        while (copyNumber > 0) {
            int digit = copyNumber % RADIX;
            reverseNumber = reverseNumber * RADIX + digit;
            copyNumber /= RADIX;
        }

        return reverseNumber;
    }

    private static int calculateReversedDescendant(int descendant) {
        int reversedDescendant = 0;
        int copyDescendant = descendant;

        if (isNumberCountDigitsOdd(copyDescendant)) {
            reversedDescendant += copyDescendant % RADIX;
            copyDescendant /= RADIX;
        }
        while (copyDescendant > 0) {
            int digitsSum = copyDescendant % RADIX + copyDescendant / RADIX % RADIX;
            if (digitsSum > RADIX - 1) {
                digitsSum = digitsSum % RADIX * RADIX + digitsSum / RADIX;
                reversedDescendant = reversedDescendant * SQUARE_RADIX + digitsSum;
            } else {
                reversedDescendant = reversedDescendant * RADIX + digitsSum;
            }
            copyDescendant /= SQUARE_RADIX;
        }

        return reversedDescendant;
    }

    public static boolean isPalindromeDescendant(int number) {
        LOGGER.trace("Determine whether a number or its descendants is a palindrome {}", number);

        if (number < 0) {
            throw new IllegalArgumentException("Number cannot be negative");
        }

        int reversedNumber;
        int descendant;
        int reversedDescendant;
        int copyNumber = number;

        do {
            descendant = copyNumber;
            reversedNumber = reverseNumber(copyNumber);

            if (reversedNumber == descendant) {
                return true;
            }

            reversedDescendant = calculateReversedDescendant(descendant);
            copyNumber = reverseNumber(reversedDescendant);

            LOGGER.trace("Descendant for {} is {}", descendant, copyNumber);
        } while (copyNumber > RADIX - 1);

        return false;
    }
}
