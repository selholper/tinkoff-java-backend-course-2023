package edu.project1;

import edu.project1.Configuration.InMemoryConfiguration;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
public class TestInMemoryConfiguration {
    @Test
    void testInMemoryConfiguration() {
        assertThatThrownBy(
            () -> new InMemoryConfiguration(-1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new InMemoryConfiguration(0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertDoesNotThrow(
            () -> new InMemoryConfiguration(1)
        );
    }

}
