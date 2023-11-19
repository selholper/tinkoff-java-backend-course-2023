package edu.hw6.Task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestTask6 {
    @Test
    void testPortScanner_doesNotThrowAnyException() {
        assertDoesNotThrow(Task6::portScanner);
    }
}
