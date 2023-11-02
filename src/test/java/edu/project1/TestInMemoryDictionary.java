package edu.project1;

import edu.project1.Dictionary.InMemoryDictionary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestInMemoryDictionary {
    @Test
    void testGetSomeWord_ShouldNotThrowAnyException() {
        InMemoryDictionary inMemoryDictionary = new InMemoryDictionary();
        assertDoesNotThrow(inMemoryDictionary::getSomeWord);
    }
}
