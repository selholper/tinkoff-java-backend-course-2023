package edu.hw5.Task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask5 {
    @ParameterizedTest
    @NullSource
    void testValidateCarNumber_shouldReturnNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task5.validateCarNumber(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "",
            "askddjfhhsadf",
            "А001ВС00",
            "А001ВС001",
            "А001ВС1912",
            "Я123МС21"
        }
    )
    void testValidateCarNumber_shouldReturnFalse(String string) {
        assertThat(Task5.validateCarNumber(string)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "А123ВЕ777",
            "О777ОО177",
            "О732АВ71",
            "О901КК31",
            "М731НВ414"
        }
    )
    void testValidateCarNumber_shouldReturnTrue(String string) {
        assertThat(Task5.validateCarNumber(string)).isTrue();
    }
}
