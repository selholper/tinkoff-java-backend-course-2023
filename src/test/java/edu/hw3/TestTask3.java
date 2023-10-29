package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {
    private static Stream<Arguments> testFreqDict_shouldReturnFreqDict() {
        return Stream.of(
            Arguments.of(Arrays.asList("a", "bb", "a", "bb"),
                Map.of(
                    "bb", 2,
                    "a", 2
                )
            ),

            Arguments.of(Arrays.asList("this", "and", "that", "and"),
                Map.of(
                    "that", 1,
                    "and", 2,
                    "this", 1
                )
            ),

            Arguments.of(Arrays.asList("код", "код", "код", "bug"),
                Map.of(
                    "код", 3,
                    "bug", 1
                )
            ),

            Arguments.of(Arrays.asList(1, 1, 2, 2),
                Map.of(
                    1, 2,
                    2, 2
                )
            ),

            Arguments.of(Arrays.asList(1.1, 1.1, 2.3, 2.5),
                Map.of(
                    1.1, 2,
                    2.3, 1,
                    2.5, 1
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы метода создания частотного словаря для null списка")
    void testFreqDict_shouldReturnNullPointerException(List<?> list) {
        assertThatThrownBy(
            () -> Task3.freqDict(list)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Тестирование работы метода создания частотного словаря для пустого списка")
    void testFreqDict_shouldReturnEmptyFreqDict(List<?> list) {
        Map<?, Integer> emptyMap = new HashMap<>();
        assertEquals(Task3.freqDict(list), emptyMap);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы метода создания частотного словаря для корректного списка")
    void testFreqDict_shouldReturnFreqDict(List<?> list, Map<?, Integer> freqDict) {
        assertEquals(Task3.freqDict(list), freqDict);
    }
}
