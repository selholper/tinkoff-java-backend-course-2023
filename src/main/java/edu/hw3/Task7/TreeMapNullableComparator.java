package edu.hw3.Task7;

import java.util.Comparator;

public class TreeMapNullableComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T oneObject, T anotherOoject) {
        if (oneObject == null && anotherOoject == null) {
            return 0;
        }

        if (oneObject == null) {
            return -1;
        }

        if (anotherOoject == null) {
            return 1;
        }

        return oneObject.compareTo(anotherOoject);
    }
}
