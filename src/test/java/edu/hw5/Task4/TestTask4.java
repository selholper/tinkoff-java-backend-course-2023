package edu.hw5.Task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask4 {
    @ParameterizedTest
    @NullSource
    void testIsValidPassword_shouldReturnNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task4.isValidPassword(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "askdjhfkjsadhfsd",
            "",
            "ajkhew rtuq2354iq2riawegfjykwetruoywrtyi",
            "ksadzgfjhsdhf",
            "()_++_(+_??>?\"\"'\\/(){}[]"
        }
    )
    void testIsValidPassword_shouldReturnFalse(String string) {
        assertThat(Task4.isValidPassword(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "|",
            "*",
            "&",
            "^",
            "%",
            "$",
            "#",
            "@",
            "!",
            "~",
            "kjsadfjhsdafgjhsdagjhasdfg|ksadfhjsdgaf",
            "shdjagfghsdgaf!",
            "@aksdjfgjhasgfjkhsadfg"
        }
    )
    void testIsValidPassword_shouldReturnTrue(String string) {
        assertThat(Task4.isValidPassword(string)).isTrue();
    }
}
