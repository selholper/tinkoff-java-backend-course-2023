package edu.project3;

import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.NginxLog;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestDateLogFilter {
    private static Stream<Arguments> testHasPassedFilter_shouldReturnResult() {
        return Stream.of(
            Arguments.of(
                new DateLogFilter("2023-03-22", "2023-04-22"),
                NginxLog.builder()
                    .timeLocal(OffsetDateTime.of(LocalDate.of(2023, 3, 24),
                        LocalTime.MIDNIGHT, ZoneOffset.UTC))
                    .build(),
                true
            ),

            Arguments.of(
                new DateLogFilter("2023-03-22", "2023-04-22"),
                NginxLog.builder()
                    .timeLocal(OffsetDateTime.of(LocalDate.of(2023, 4, 24),
                        LocalTime.MIDNIGHT, ZoneOffset.UTC))
                    .build(),
                false
            ),

            Arguments.of(
                new DateLogFilter("0123-03-22", "2023-04-22"),
                NginxLog.builder()
                    .timeLocal(OffsetDateTime.of(LocalDate.of(2023, 4, 24),
                        LocalTime.MIDNIGHT, ZoneOffset.UTC))
                    .build(),
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testHasPassedFilter_shouldReturnResult(
        LogFilter testFilter,
        NginxLog testLog,
        boolean expected
    ) {
        boolean actual = testFilter.hasPassedFilter(testLog);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDateLogFilterClass_shouldThrowIllegalArgumentException() {
        assertThatThrownBy(() -> new DateLogFilter(
            "2023.03.22",
            "2023.04.22"
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
