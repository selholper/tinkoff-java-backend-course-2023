package edu.hw7.Task4;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMonteCarloPICalculator {
    @ParameterizedTest
    @ValueSource(
        ints = {1_000_000, 10_000_000, 100_000_000}
    )
    void testCalculateInSingleThreadMode_shouldReturnResultWithSetPrecision(long totalCount) {
        Assertions.assertThat(
            MonteCarloPICalculator.calculateInSingleThreadMode(totalCount))
            .isCloseTo(Math.PI, Offset.offset(1_000_000d / totalCount)
        );
    }

    @ParameterizedTest
    @ValueSource(
        ints = {10_000_000, 100_000_000, 1_000_000_000}
    )
    void testCalculateInMultiThreadMode_shouldReturnResultWithSetPrecision(long totalCount) {
        Assertions.assertThat(
            MonteCarloPICalculator.calculateInMultiThreadMode(1_000_000_000))
            .isCloseTo(Math.PI, Offset.offset(1_000_000d / totalCount)
        );
    }
}
