package edu.hw7.Task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAtomicCounter {
    @ParameterizedTest
    @ValueSource(
        ints = {1, 5, 10, 100, 10000}
    )
    void testAtomicCounter_shouldReturnResult(int threadsNumber) {
        AtomicCounter counter = new AtomicCounter();
        IncrementerThread[] threads = new IncrementerThread[threadsNumber];

        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new IncrementerThread(counter);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception ignored) {
            }
        }

        assertEquals(counter.getCount(), threadsNumber);
    }
}
