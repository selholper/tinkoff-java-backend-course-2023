package edu.hw3.Task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask4 {
    private static Stream<Arguments> testConvertToRoman_shouldReturnRomanNumber() {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(12, "XII"),
            Arguments.of(6, "VI"),
            Arguments.of(4, "IV"),
            Arguments.of(18, "XVIII"),
            Arguments.of(33, "XXXIII"),
            Arguments.of(21, "XXI"),
            Arguments.of(3, "III"),
            Arguments.of(26, "XXVI"),
            Arguments.of(28, "XXVIII"),
            Arguments.of(196, "CXCVI"),
            Arguments.of(149, "CXLIX"),
            Arguments.of(424, "CDXXIV"),
            Arguments.of(296, "CCXCVI"),
            Arguments.of(544, "DXLIV"),
            Arguments.of(573, "DLXXIII"),
            Arguments.of(789, "DCCLXXXIX"),
            Arguments.of(728, "DCCXXVIII"),
            Arguments.of(1953, "MCMLIII"),
            Arguments.of(713, "DCCXIII"),
            Arguments.of(3999, "MMMCMXCIX")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 12345, 4000, 156234, -718235})
    @DisplayName("Тестирование работы метода для некорректного арабского числа")
    void testConvertToRoman_shouldReturnIllegalArgumentException(int number) {
        assertThatThrownBy(
            () -> Task4.convertToRoman(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы метода для корректного арабского числа")
    void testConvertToRoman_shouldReturnRomanNumber(int number, String string) {
        assertEquals(Task4.convertToRoman(number), string);
    }
}
