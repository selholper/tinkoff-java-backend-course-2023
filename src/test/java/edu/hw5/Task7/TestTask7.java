package edu.hw5.Task7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask7 {
    @ParameterizedTest
    @NullSource
    void testsIsSatisfyFirstCondition_shouldThrowNullPointerExceptionForNullString(String string) {
        assertThatThrownBy(
            () -> Task7.isSatisfyFirstCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testsIsSatisfySecondCondition_shouldThrowNullPointerExceptionForNullString(String string) {
        assertThatThrownBy(
            () -> Task7.isSatisfySecondCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testsIsSatisfyThirdCondition_shouldThrowNullPointerExceptionForNullString(String string) {
        assertThatThrownBy(
            () -> Task7.isSatisfyThirdCondition(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "asnc",
            "00",
            "101",
            "1201"
        }
    )
    void testsIsSatisfyFirstCondition_shouldReturnFalse(String string) {
        assertThat(Task7.isSatisfyFirstCondition(string)).isFalse();
    }


    @ParameterizedTest
    @ValueSource(
        strings = {
            "1001",
            "000",
            "110",
            "110111111",
            "0101010101"
        }
    )
    void testsIsSatisfyFirstCondition_shouldReturnTrue(String string) {
        assertThat(Task7.isSatisfyFirstCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "10",
            "01",
            "asnc",
            "aa",
            "100",
            "101010010101010"
        }
    )
    void testsIsSatisfySecondCondition_shouldReturnFalse(String string) {
        assertThat(Task7.isSatisfySecondCondition(string)).isFalse();
    }


    @ParameterizedTest
    @ValueSource(
        strings = {
            "0",
            "1",
            "00",
            "11",
            "1001",
            "000",
            "1101",
            "110111111",
            "01010101010"
        }
    )
    void testsIsSatisfySecondCondition_shouldReturnTrue(String string) {
        assertThat(Task7.isSatisfySecondCondition(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "1001",
            "0111231",
            "asnc",
            "a",
            "ab",
            "abc"
        }
    )
    void testsIsSatisfyThirdCondition_shouldReturnFalse(String string) {
        assertThat(Task7.isSatisfyThirdCondition(string)).isFalse();
    }


    @ParameterizedTest
    @ValueSource(
        strings = {
            "0",
            "1",
            "00",
            "11",
            "000",
            "011",
            "111"
        }
    )
    void testsIsSatisfyThirdCondition_shouldReturnTrue(String string) {
        assertThat(Task7.isSatisfyThirdCondition(string)).isTrue();
    }
}
