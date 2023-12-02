package edu.hw8.Task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask2 {
    @Test
    @SneakyThrows
    void testCalculateFibonacciMethod_shouldReturnCorrectResultInMultiThreadMode() {
        ThreadPool threadPool = FixedThreadPool.create(Runtime.getRuntime().availableProcessors());
        List<Integer> excepted = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
        final List<Integer> actual = new CopyOnWriteArrayList<>();

        for (int i = 0; i <= 12; ++i) {
            final int n = i;
            threadPool.execute(() -> actual.add(Fibonacci.calculateFibonacci(n)));
        }

        assertThat(actual).containsExactlyInAnyOrderElementsOf(excepted);
        threadPool.close();
    }

    @Test
    void testFixedThreadPoolCreateMethod_shouldThrowIllegalArgumentException() {
        assertThatThrownBy(
            () -> FixedThreadPool.create(0)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
