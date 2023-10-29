package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask6 {
    private static Stream<Arguments> testBestOfferStockMarket_shouldReturnIllegalArgumentException() {
        return Stream.of(
            Arguments.of(new Stock("123", -1)),
            Arguments.of(new Stock("ABC", -1.1)),
            Arguments.of(new Stock("123", 123))
        );
    }

    private static Stream<Arguments> testBestOfferStockMarket_shouldReturnMostValuableStock() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Stock("AAA", 100),
                    new Stock("ABC", 100),
                    new Stock("TCS", 36.74),
                    new Stock("TCSG", 36.36)
                )
            ),

            Arguments.of(
                List.of(
                    new Stock("IBM", 431.34),
                    new Stock("IBMG", 431.34),
                    new Stock("ZUBR", 431.34)
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы класса (записи) биржи для null приоритетной очереди")
    void testBestOfferStockMarket_shouldReturnIllegalArgumentExceptionForNullPriorityQueue(
        PriorityQueue<Stock> priorityStocks) {
        assertThatThrownBy(
            () -> new StockMarket.BestOfferStockMarket(priorityStocks)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы классов (записи) биржи для null объекта класса акции")
    void testBestOfferStockMarket_shouldReturnIllegalArgumentExceptionForNullStock(
        Stock stock) {
        PriorityQueue <Stock> priorityQueue = new PriorityQueue<>();
        StockMarket bestOfferStockMarket = new StockMarket.BestOfferStockMarket(priorityQueue);

        assertThatThrownBy(
            () -> bestOfferStockMarket.add(stock)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> bestOfferStockMarket.remove(stock)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы класса (записи) акции для null объекта класса (записи) акции")
    void testStock_shouldReturnIllegalArgumentExceptionNullContact(Stock stock) {
        Stock thisStock = new Stock("ABC", 0);

        assertThatThrownBy(
            () -> thisStock.compareTo(stock)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы класса (записи) акции для null строки")
    void testStock_shouldReturnIllegalArgumentExceptionForNullString(String string) {
        assertThatThrownBy(
            () -> new Stock(string, 123)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Тестирование работы класса (записи) биржи для пустой приоритетной очереди")
    void testBestOfferStockMarket_shouldReturnRuntimeExceptionForNullPriorityQueue(
        PriorityQueue <Stock> priorityQueue) {
        StockMarket bestOfferStockMarket = new StockMarket.BestOfferStockMarket(priorityQueue);
        assertThatThrownBy(
            () -> bestOfferStockMarket.remove(new Stock("ABC", 12))
        ).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(
            bestOfferStockMarket::mostValuableStock
        ).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы класса (записи) биржи для некорректных входных данных")
    void testBestOfferStockMarket_shouldReturnIllegalArgumentException(Stock stock) {
        PriorityQueue <Stock> priorityQueue = new PriorityQueue<>();
        StockMarket bestOfferStockMarket = new StockMarket.BestOfferStockMarket(priorityQueue);

        assertThatThrownBy(
            () -> bestOfferStockMarket.add(stock)
        ).isInstanceOf(IllegalArgumentException.class);

        bestOfferStockMarket.add(new Stock("ABC", 123));
        assertThatThrownBy(
            () -> bestOfferStockMarket.remove(stock)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы класса (записи) биржи для корректных входных данных")
    void testBestOfferStockMarket_shouldReturnMostValuableStock(List<Stock> stocks) {
        PriorityQueue <Stock> priorityQueue = new PriorityQueue<>();
        StockMarket bestOfferStockMarket = new StockMarket.BestOfferStockMarket(priorityQueue);

        for (Stock stock : stocks) {
            bestOfferStockMarket.add(stock);
        }

        for (Stock stock : stocks) {
            assertEquals(bestOfferStockMarket.mostValuableStock(), stock);
            bestOfferStockMarket.remove(stock);
        }
    }
}
