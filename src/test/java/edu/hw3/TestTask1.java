package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {
    private static Stream <Arguments> testAtbash_shouldReturnEncryptedString() {
        return Stream.of(
            Arguments.of("123", "123"),
            Arguments.of("abc","zyx"),
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("Any fool can write code that a "
                + "computer can understand. Good programmers write code "
                + "that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw."
                + " Tllw kiltiznnvih dirgv xlwv"
                + " gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"),
            Arguments.of("Текст на кириллице", "Текст на кириллице")
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы метода шифра Атбаша для null строки")
    void testAtbash_shouldThrowNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task1.atbash(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Тестирование работы метода шифра Атбаша для пусой строки")
    void testAtbash_shouldReturnEmptyEncryptedString(String string) {
        assertEquals(Task1.atbash(string), "");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы метода шифра Атбаша для корректной строки")
    void testAtbash_shouldReturnEncryptedString(String string, String result) {
        assertEquals(Task1.atbash(string), result);
    }
}
