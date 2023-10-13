package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask6 {
    @Test
    @DisplayName("Подсчитываем количество шагов, которвые нужно сделать для " +
        "корректно заданного числа, чтобы получить 6174")
    void testCorrectCountK() {
        // given
        int correctNumber = 6621;
        // when
        int stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(5);

        // given
        correctNumber = 6554;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(4);

        // given
        correctNumber = 1234;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(3);

        // given
        correctNumber = 6174;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(0);

        // given
        correctNumber = 1001;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(4);

        // given
        correctNumber = 1100;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(4);

        // given
        correctNumber = 1010;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(4);

        // given
        correctNumber = 3524;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(3);

        // given
        correctNumber = 2111;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(5);

        // given
        correctNumber = 7146;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(1);

        // given
        correctNumber = 5200;
        // when
        stepsAmount = Task6.countK(correctNumber);
        // then
        assertThat(stepsAmount).isEqualTo(7);
    }

    @Test
    @DisplayName("Проверка исключений для некорректно заданного числа")
    void testIncorrectCountK() {
        assertThatThrownBy(
            () -> Task6.countK(0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(1000)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(-123654)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(10000)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(134)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
