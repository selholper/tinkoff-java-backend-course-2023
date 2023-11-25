package edu.hw7.Task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFactorialParallelCalculator {
    @ParameterizedTest
    @ValueSource(
        ints = {0, 1, 2, 5, 10, 15, 20}
    )
    void testFactorialParallelCalculator_shouldReturnResultForCorrectNumber(int number) {
        long factorial = 1;
        for (int n = 2; n <= number; ++n) {
            factorial *= n;
        }
        assertEquals(FactorialParallelCalculator.calculateFactorial(number), factorial);
    }

    @ParameterizedTest
    @ValueSource(
        ints = {-12313, -1, 21, 12736}
    )
    void testFactorialParallelCalculator_shouldThrowExceptionForIncorrectNumber(int number) {
        assertThatThrownBy(
            () -> FactorialParallelCalculator.calculateFactorial(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
