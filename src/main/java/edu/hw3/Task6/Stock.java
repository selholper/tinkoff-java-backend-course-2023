package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public record Stock(@NotNull String ticker, double price) implements Comparable<Stock> {
    @Override
    public int compareTo(@NotNull Stock anotherStock) {
        if (this.price == anotherStock.price) {
            return this.ticker.compareTo(anotherStock.ticker());
        }

        if (this.price > anotherStock.price) {
            return -1;
        }

        return 1;
    }
}
