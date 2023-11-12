package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask3 {
    @ParameterizedTest
    @NullSource
    void testParseDate_shouldReturnNullPointerExceptionForNullString(String string) {
        assertThatThrownBy(
            () -> Task3.parseDate(string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {"123", "16243", "10.10.1230", "-12/3/129", "+_!@#*&*(^@$"}
    )
    void testParseDate_shouldReturnEmptyResult(String string) {
        assertThat(Task3.parseDate(string)).isEmpty();
    }

    private static Stream<Arguments> testParseDate_shouldReturnNonEmptyResult() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                LocalDate.of(2020, 10, 10)
            ),

            Arguments.of(
                "2020-12-2",
                LocalDate.of(2020, 12, 2)
            ),

            Arguments.of(
                "1/3/1976",
                LocalDate.of(1976, 1, 3)
            ),

            Arguments.of(
                "1/3/20",
                LocalDate.of(2020, 1, 3)
            ),

            Arguments.of(
                "tomorrow",
                LocalDate.now().plusDays(1)
            ),

            Arguments.of(
                "today",
                LocalDate.now()
            ),

            Arguments.of(
                "yesterday",
                LocalDate.now().minusDays(1)
            ),

            Arguments.of(
                "1 day ago",
                LocalDate.now().minusDays(1)
            ),

            Arguments.of(
                "2234 days ago",
                LocalDate.now().minusDays(2234)
            ),

            Arguments.of(
                "1 day after",
                LocalDate.now().plusDays(1)
            ),

            Arguments.of(
                "2234 days after",
                LocalDate.now().plusDays(2234)
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testParseDate_shouldReturnNonEmptyResult(String string, LocalDate result) {
        Optional<LocalDate> date = Task3.parseDate(string);
        assertThat(date).hasValue(result);
    }
}
