package edu.hw5.Task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask8 {
    @ParameterizedTest
    @NullSource
    void testIsSatisfyFirstCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfyFirstCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testIsSatisfySecondCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfySecondCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testIsSatisfyThirdCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfyThirdCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testIsSatisfyFourthCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfyFourthCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testIsSatisfyFifthCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfyFifthCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testIsSatisfySixthCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfySixthCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testIsSatisfySeventhCondition_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task8.isSatisfySeventhCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "10",
            "00",
            "11",
            "1231",
            "123",
            "_+!",
            "1010101010101010"
        }
    )
    void testIsSatisfyFirstCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfyFirstCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "0",
            "1",
            "000",
            "111",
            "01010101010010101",
            "1010101010101010011"
        }
    )
    void testIsSatisfyFirstCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfyFirstCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "00",
            "0001",
            "111",
            "1231",
            "123",
            "_+!",
            "10101010101010101"
        }
    )
    void testIsSatisfySecondCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfySecondCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "0",
            "000",
            "10",
            "1010",
            "111111"
        }
    )
    void testIsSatisfySecondCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfySecondCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "001",
            "011",
            "0000",
            "00000",
            "1010101010101000000001",
            "_+!",
            "10101010101010101"
        }
    )
    void testIsSatisfyThirdCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfyThirdCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "000",
            "000000",
            "101010",
            "1010010010",
            "1001010101010010000"
        }
    )
    void testIsSatisfyThirdCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfyThirdCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "11",
            "111",
            "sadklfjsadf",
            "sldkfhlasdf",
            "lksdjla",
            "_+!",
            "alskdhasd"
        }
    )
    void testIsSatisfyFourthCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfyFourthCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "000",
            "000000",
            "101010",
            "1010010010",
            "1001010101010010000"
        }
    )
    void testIsSatisfyFourthCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfyFourthCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "0",
            "100",
            "10100",
            "11100",
            "1010110",
            "10101000",
            "lksdjla",
            "_+!",
            "alskdhasd"
        }
    )
    void testIsSatisfyFifthCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfyFifthCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "101",
            "111111",
            "101010",
            "1010111111",
            "111110111111"
        }
    )
    void testIsSatisfyFifthCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfyFifthCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "0",
            "1001",
            "10100",
            "11100",
            "10101000",
            "lksdjla",
            "_+!",
            "alskdhasd"
        }
    )
    void testIsSatisfySixthCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfySixthCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "00",
            "100",
            "010",
            "00100",
            "000000",
            "000000000010000000000000000"
        }
    )
    void testIsSatisfySixthCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfySixthCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "11",
            "011",
            "1101",
            "10110",
            "11100",
            "1010110",
            "10101100",
            "lksdjla",
            "_+!",
            "alskdhasd"
        }
    )
    void testIsSatisfySeventhCondition_shouldReturnFalse(String string) {
        assertThat(Task8.isSatisfySeventhCondition(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "1",
            "10",
            "100",
            "010",
            "10101",
            "101010",
            "100100000010000001001000010"
        }
    )
    void testIsSatisfySeventhCondition_shouldReturnTrue(String string) {
        assertThat(Task8.isSatisfySeventhCondition(string)).isTrue();
    }
}
