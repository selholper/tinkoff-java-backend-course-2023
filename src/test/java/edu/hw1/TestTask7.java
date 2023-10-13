package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
public class TestTask7 {
    @Test
    @DisplayName("Тестирование операции циклического сдвига влево для корректных аргументов")
    void testCorrectRotateLeft() {
        // given
        int n = 8;
        int shift = 1;
        // when
        int result = Task7.rotateLeft(n, shift);
        // then
        assertThat(result).isEqualTo(1);

        // given
        n = 17;
        shift = 2;
        // when
        result = Task7.rotateLeft(n, shift);
        // then
        assertThat(result).isEqualTo(6);

        // given
        n = 16;
        shift = 0;
        // when
        result = Task7.rotateLeft(n, shift);
        // then
        assertThat(result).isEqualTo(16);

        // given
        n = 1024 * 1024;
        shift = 22;
        // when
        result = Task7.rotateLeft(n, shift);
        // then
        assertThat(result).isEqualTo(1);

        // given
        n = 1024 * 512;
        shift = 108;
        // when
        result = Task7.rotateLeft(n, shift);
        // then
        assertThat(result).isEqualTo(128);
    }

    @Test
    @DisplayName("Проверка исключений для операции циклического сдвига влево")
    void testIncorrectRotateLeft() {
        assertThatThrownBy(
            () -> Task7.rotateLeft(-1, 0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateLeft(0, 0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateLeft(0, -1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateLeft(1, -1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateLeft(232, -4)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тестирование операции циклического сдвига вправо для корректных аргументов")
    void testCorrectRotateRight() {
        // given
        int n = 8;
        int shift = 1;
        // when
        int result = Task7.rotateRight(n, shift);
        // then
        assertThat(result).isEqualTo(4);

        // given
        n = 17;
        shift = 2;
        // when
        result = Task7.rotateRight(n, shift);
        // then
        assertThat(result).isEqualTo(12);

        // given
        n = 16;
        shift = 0;
        // when
        result = Task7.rotateRight(n, shift);
        // then
        assertThat(result).isEqualTo(16);

        // given
        n = 1024 * 1024;
        shift = 22;
        // when
        result = Task7.rotateRight(n, shift);
        // then
        assertThat(result).isEqualTo(1024 * 512);

        // given
        n = 1024 * 512;
        shift = 108;
        // when
        result = Task7.rotateRight(n, shift);
        // then
        assertThat(result).isEqualTo(2048);
    }
    @Test
    @DisplayName("Проверка иключений для операции циклического сдвига вправо")
    void testIncorrectRotateRight() {
        assertThatThrownBy(
            () -> Task7.rotateRight(-1, 0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateRight(0, 0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateRight(0, -1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateRight(1, -1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task7.rotateRight(232, -4)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
