package edu.hw7.Task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAtomicCounter {
    @ParameterizedTest
    @ValueSource(
        ints = {1, 5, 10}
    )
    public void testAtomicCounter(int threadsNumber) {
        AtomicCounter counter = new AtomicCounter();
        Incrementer[] threads = new Incrementer[threadsNumber];

        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new Incrementer(counter);
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
