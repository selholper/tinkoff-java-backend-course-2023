package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {
    private static Stream <Arguments> testClusterize_shouldReturnClusteredString() {
        return Stream.of(
            Arguments.of("()()()", Arrays.asList("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())",
                List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))",
                List.of("((())())", "(()(()()))")),
            Arguments.of("((()))()()",
                List.of("((()))", "()", "()"))
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы метода Кластеризации скобок для null строки")
    void testClusterize_shouldReturnNullPointerException(String string) {
        assertThatThrownBy(
            () -> Task2.clusterize(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Тестирование работы метода Кластеризации скобок для пустой строки")
    void testClusterize_shouldReturnEmptyClusteredString(String string) {
        List <String> emptyList = new ArrayList<>();
        assertEquals(Task2.clusterize(string), emptyList);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "(()", ")", "()())", "()())(", "(())1()"})
    @DisplayName("Тестирование работы метода Кластеризации для некорректных строк")
    void testClusterize_shouldReturnIllegalArgumentException(String string) {
        assertThatThrownBy(
            () -> Task2.clusterize(string)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы метода Кластеризации для корректных строк")
    void testClusterize_shouldReturnClusteredString(String string, List<String> clusteredString) {
        assertEquals(Task2.clusterize(string), clusteredString);
    }
}
