package edu.hw5.Task2;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {
    private static Stream<Arguments> testFindAllFridayTheThirteenthInTheYear_testShouldReturnCorrectResult() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),

            Arguments.of(
                2001,
                List.of(
                    LocalDate.of(2001, 4, 13),
                    LocalDate.of(2001, 7, 13)
                )
            ),

            Arguments.of(
                2023,
                List.of(
                    LocalDate.of(2023, 1, 13),
                    LocalDate.of(2023, 10, 13)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testFindAllFridayTheThirteenthInTheYear_testShouldReturnCorrectResult(
        int year,
        List<LocalDate> exceptedList
    ) {
        assertEquals(Task2.findAllFridayTheThirteenthInTheYear(year), exceptedList);
    }

    private static Stream<Arguments> testFindNextFridayTheThirteen_shouldReturnCorrectResult() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2023, 10, 12),
                LocalDate.of(2023, 10, 13)
            ),

            Arguments.of(
                LocalDate.of(2023, 10, 13),
                LocalDate.of(2024, 9, 13)
            ),

            Arguments.of(
                LocalDate.of(0, 1, 1),
                LocalDate.of(0, 10, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testFindNextFridayTheThirteen_shouldReturnCorrectResult(
        LocalDate date,
        LocalDate result
    ) {
        assertEquals(Task2.findNextFridayTheThirteen(date), result);
    }
}
