package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class TestTask2 {
    @Test
    @DisplayName("Подчсёт количества цифр числа в десятичной форме")
    void testCountDigits() {
        // given
        int decimalNumber = 0;
        // when
        int decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(1);

        // given
        decimalNumber = 1;
        // when
        decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(1);

        // given
        decimalNumber = -1;
        // when
        decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(1);

        // given
        decimalNumber = -12300;
        // when
        decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(5);

        // given
        decimalNumber = 12730;
        // when
        decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(5);

        // given
        decimalNumber = 101010;
        // when
        decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(6);

        // given
        decimalNumber = -1234510;
        // when
        decimalNumberDigitsCount = Task2.countDigits(decimalNumber);
        // then
        assertThat(decimalNumberDigitsCount).isEqualTo(7);
    }


}
