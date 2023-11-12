package edu.hw5.Task1;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {
    @ParameterizedTest
    @NullSource
    void testGetAverageTimeOfVisitorsStay_shouldReturnNullPointerExceptionForNullList(
        List<String> list) {
        assertThatThrownBy(
            () -> Task1.getAverageTimeOfVisitorsStay(list)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testGetAverageTimeOfVisitorsStay_shouldReturnNullPointerExceptionForListWithNullString(
        String string
    ) {
        List <String> list = new ArrayList<>();
        list.add(string);
        assertThatThrownBy(
            () -> Task1.getAverageTimeOfVisitorsStay(list)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testGetAverageTimeOfVisitorsStay_shouldReturnIllegalArgumentExceptionForEmptyList(
        List<String> list) {
        assertThatThrownBy(
            () -> Task1.getAverageTimeOfVisitorsStay(list)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
        "2022-03-12, 20:20 & 2022-03-12, 23:50",
        "- 2022-03-12, 23:50",
        "2022-03-12, 20:20 -"
        }
    )
    void testGetAverageTimeOfVisitorsStay_shouldReturnIllegalArgumentExceptionForWrongFormatOfString(
        String string) {
        List <String> list = new ArrayList<>();
        list.add(string);
        assertThatThrownBy(
            () -> Task1.getAverageTimeOfVisitorsStay(list)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "2022-03-12, 20:20 - 2022-03-12, 23:5000",
            "2022-02-30, 20:20 - 2022-03-12, 23:5000",
            "2022-03-30, 20:20 - 2022-03-12, 23:5000",
            "aaaa-03-12, 20:20 - 2022-03-12, 23:50",
            " - "
        }
    )
    void testGetAverageTimeOfVisitorsStay_shouldReturnDateTimeParseException(
        String string) {
        List <String> list = new ArrayList<>();
        list.add(string);
        assertThatThrownBy(
            () -> Task1.getAverageTimeOfVisitorsStay(list)
        ).isInstanceOf(DateTimeParseException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "2022-03-12, 23:50 - 2022-03-12, 20:20"
        }
    )
    void testGetAverageTimeOfVisitorsStay_shouldReturnIllegalArgumentExceptionForRightFormatOfString(
        String string) {
        List <String> list = new ArrayList<>();
        list.add(string);
        assertThatThrownBy(
            () -> Task1.getAverageTimeOfVisitorsStay(list)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> testGetAverageTimeOfVisitorsStay_shouldReturnCorrectResult() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                "3ч 40м"
            ),

            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:50"
                ),
                "3ч 55м"
            ),

            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-13, 20:20",
                    "2022-04-01, 21:30 - 2022-04-02, 23:30"
                ),
                "25ч 0м"
            ),

            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2023-03-12, 20:20",
                    "2022-04-01, 21:30 - 2022-04-06, 21:30"
                ),
                "4440ч 0м"
            ),

            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2023-03-12, 20:20",
                    "2022-04-01, 21:30 - 2022-04-06, 23:28"
                ),
                "4440ч 59м"
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetAverageTimeOfVisitorsStay_shouldReturnCorrectResult(List<String> list, String result) {
        assertEquals(Task1.getAverageTimeOfVisitorsStay(list), result);
    }
}
