package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task3 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        Objects.requireNonNull(array1);
        Objects.requireNonNull(array2);

        if (array1.length == 0 || array2.length == 0) {
            throw new IndexOutOfBoundsException("Arrays can't be empty");
        }

        LOGGER.trace("Processing arrays {} and {}", array1, array2);
        int minElementArray1 = array1[0];
        int maxElementArray1 = array1[0];
        int minElementArray2 = array2[0];
        int maxElementArray2 = array2[0];

        for (int i = 1; i < array1.length; ++i) {
            minElementArray1 = Math.min(minElementArray1, array1[i]);
            maxElementArray1 = Math.max(maxElementArray1, array1[i]);
        }
        for (int i = 1; i < array2.length; ++i) {
            minElementArray2 = Math.min(minElementArray2, array2[i]);
            maxElementArray2 = Math.max(maxElementArray2, array2[i]);
        }

        return (minElementArray1 > minElementArray2
            && maxElementArray1 < maxElementArray2);
    }
}
