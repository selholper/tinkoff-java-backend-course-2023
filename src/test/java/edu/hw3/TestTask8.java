package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask8 {
    private static Stream<Arguments> testBackwardIterator_shouldReturnElementsInReverseOrder() {
        return Stream.of(
            Arguments.of(
                List.of("1", "2", "3")
            ),

            Arguments.of(
                List.of(1, 2, 3)
            ),

            Arguments.of(
                List.of(1.1, 2.2, 3.3)
            )
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы класса обратного итератора, обрабатывающего null список")
    void testBackwardIterator_shouldReturnNullPointerException(List<String> list) {
        assertThatThrownBy(
            () -> new BackwardIterator<>(list)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Тестирование работы методов класса обратного итератора для пустого списка")
    void testBackwardIterator_shouldReturnRuntimeException(List<String> list) {
        BackwardIterator <String> backwardIterator = new BackwardIterator<>(list);

        assertThatThrownBy(
            backwardIterator::next
        ).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы методов класса обратного итератора для пустого списка")
    void testBackwardIterator_shouldReturnElementsInReverseOrder(List<?> list) {
        BackwardIterator <?> reverseOrderList = new BackwardIterator<>(list);

        int listCurrentIndex = list.size();
        while (reverseOrderList.hasNext()) {
            assertEquals(reverseOrderList.next(), list.get(--listCurrentIndex));
        }
    }
}
