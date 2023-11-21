package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int currentIndex;

    public BackwardIterator(List<T> list) {
        Objects.requireNonNull(list);

        this.list = list;
        currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex > -1;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Next element does not exist");
        }

        return list.get(currentIndex--);
    }
}
