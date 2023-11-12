package edu.hw5.Task6;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask6 {
    @ParameterizedTest
    @NullSource
    void testIsOneStringSubsequenceOfAnother_shouldReturnNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task6.isOneStringSubsequenceOfAnother(string, string)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task6.isOneStringSubsequenceOfAnother(string, "string")
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task6.isOneStringSubsequenceOfAnother("string", string)
        ).isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> testIsOneStringSubsequenceOfAnother_shouldReturnFalse() {
        return Stream.of(
            Arguments.of(
                "123", ""
            ),

            Arguments.of(
                "123", "12"
            ),

            Arguments.of(
                "AJSHFG", "901273"
            ),

            Arguments.of(
                "#!@#", "901273"
            ),

            Arguments.of(
                "abcabcabc", "gldsabc"
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testIsOneStringSubsequenceOfAnother_shouldReturnFalse(String s, String t) {
        assertThat(Task6.isOneStringSubsequenceOfAnother(s, t)).isFalse();
    }

    private static Stream<Arguments> testIsOneStringSubsequenceOfAnother_shouldReturnTrue() {
        return Stream.of(
            Arguments.of(
                "", ""
            ),

            Arguments.of(
                "123", "123"
            ),

            Arguments.of(
                "123", "123123"
            ),

            Arguments.of(
                "123", "ASHJDGHASD123*(@^$&@*&$"
            ),

            Arguments.of(
                "abcabcabc", "abcabcabcabcabcabc"
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testIsOneStringSubsequenceOfAnother_shouldReturnTrue(String s, String t) {
        assertThat(Task6.isOneStringSubsequenceOfAnother(s, t)).isTrue();
    }
}
