package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
public class TestTask4 {
    @Test
    @DisplayName("Исправление корректной строки с перепутанными парами символов")
    void testFixCorrectString() {
        // given
        String brokenString = "123456";
        // when
        String fixedString = Task4.fixString(brokenString);
        // then
        assertThat(fixedString).isEqualTo("214365");

        // given
        brokenString = "hTsii  s aimex dpus rtni.g";
        // when
        fixedString = Task4.fixString(brokenString);
        // then
        assertThat(fixedString).isEqualTo("This is a mixed up string.");

        // given
        brokenString = "badce";
        // when
        fixedString = Task4.fixString(brokenString);
        // then
        assertThat(fixedString).isEqualTo("abcde");

        // given
        brokenString = "";
        // when
        fixedString = Task4.fixString(brokenString);
        // then
        assertThat(fixedString).isEqualTo("");

        // given
        brokenString = "+--+//";
        // when
        fixedString = Task4.fixString(brokenString);
        // then
        assertThat(fixedString).isEqualTo("-++-//");

        // given
        brokenString = "popcount";
        // when
        fixedString = Task4.fixString(brokenString);
        // then
        assertThat(fixedString).isEqualTo("opcpuotn");
    }

    @Test
    @DisplayName("Проверка иключений для некорректной строки с перепутанными парами символов")
    void testFixIncorrectString() {
        assertThatThrownBy(
            () -> Task4.fixString(null)
        ).isInstanceOf(NullPointerException.class);
    }
}
