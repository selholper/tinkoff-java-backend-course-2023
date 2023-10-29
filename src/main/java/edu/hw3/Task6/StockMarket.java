package edu.hw3.Task6;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public sealed interface StockMarket {
    void add(Stock stock);

    void remove(Stock stock);

    Stock mostValuableStock();

    record BestOfferStockMarket(PriorityQueue<Stock> priorityStocks) implements StockMarket {
        public BestOfferStockMarket {
            Objects.requireNonNull(priorityStocks);
        }

        private static final Pattern TICKER_PATTERN =
            Pattern.compile("^[A-Z]{3,5}$");

        @Override
        public void add(Stock stock) {
            Objects.requireNonNull(stock);

            Matcher matcherTickerPattern = TICKER_PATTERN.matcher(stock.ticker());

            if (!matcherTickerPattern.matches()) {
                throw new IllegalArgumentException("Invalid stock try to add: stock ticker is incorrect");
            }

            if (stock.price() < 0) {
                throw new IllegalArgumentException("Invalid stock try to add: stock price is negative");
            }

            priorityStocks.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            Objects.requireNonNull(stock);

            Matcher matcherTickerPattern = TICKER_PATTERN.matcher(stock.ticker());

            if (priorityStocks.isEmpty()) {
                throw new RuntimeException("Try to remove stock from empty stock market");
            }

            if (!matcherTickerPattern.matches()) {
                throw new IllegalArgumentException("Invalid stock try to remove: stock ticker is incorrect");
            }

            if (stock.price() < 0) {
                throw new IllegalArgumentException("Invalid stock try to remove: stock price is negative");
            }

            priorityStocks.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            if (priorityStocks.isEmpty()) {
                throw new RuntimeException("Try to get most valuable stock from empty stock market");
            }

            return priorityStocks.peek();
        }
    }
}
