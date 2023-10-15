package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
public class TestTask5 {
    @Test
    @DisplayName("Проверяем является ли корректное число или его потомок палиндромом")
    void testCorrectPalindromeDescendant() {
        // given
        int correctNumber = 11211230;
        // when
        boolean isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 13001120;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 23336014;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 11;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 0;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 1;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 9;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 0;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 101;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 12344321;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 10;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isFalse();

        // given
        correctNumber = 123;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 124;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isFalse();

        // given
        correctNumber = 12345;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isFalse();

        // given
        correctNumber = 89999;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isFalse();

        // given
        correctNumber = 98988;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 561316781;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 133913;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 92;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isTrue();

        // given
        correctNumber = 91;
        // when
        isPalindrome = Task5.isPalindromeDescendant(correctNumber);
        // then
        assertThat(isPalindrome).isFalse();
    }

    @Test
    @DisplayName("Проверка исключений для некорректного числа")
    void testIncorrectPalindromeDescendant() {
        assertThatThrownBy(
            () ->Task5.isPalindromeDescendant(-1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () ->Task5.isPalindromeDescendant(-1651234)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () ->Task5.isPalindromeDescendant(-78254145)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
