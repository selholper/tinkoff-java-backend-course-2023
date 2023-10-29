package edu.hw3.Task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record Stock(String ticker, double price) implements Comparable<Stock> {
    public Stock {
        Objects.requireNonNull(ticker);
    }

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
