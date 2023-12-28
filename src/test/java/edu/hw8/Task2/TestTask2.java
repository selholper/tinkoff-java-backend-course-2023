package edu.hw8.Task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestTask2 {
    @Test
    @SneakyThrows
    void testCalculateFibonacciMethod_shouldReturnCorrectResultInMultiThreadMode() {
        ThreadPool threadPool = FixedThreadPool.create(Runtime.getRuntime().availableProcessors());
        final List<Integer> actual = new CopyOnWriteArrayList<>();

        for (int i = 0; i <= 12; ++i) {
            final int n = i;
            threadPool.execute(() -> assertDoesNotThrow(
                () -> actual.add(Fibonacci.calculateFibonacci(n))
            ));
        }

        threadPool.close();
    }

    @Test
    void testFixedThreadPoolCreateMethod_shouldThrowIllegalArgumentException() {
        assertThatThrownBy(
            () -> FixedThreadPool.create(0)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
