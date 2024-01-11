package edu.project3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestMain {
    @Test
    void testMainMethod_shouldThrowIllegalArgumentException() {
        assertThatThrownBy(
            () -> Main.main(new String[]{})
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
