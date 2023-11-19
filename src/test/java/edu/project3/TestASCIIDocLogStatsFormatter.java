package edu.project3;

import edu.project3.formatter.ASCIIDocLogStatsFormatter;
import edu.project3.model.FormatterComponent;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestASCIIDocLogStatsFormatter {
    private static Stream<Arguments> testFormatMethod_shouldReturnFormattedStringInAsciiDocFormat() {
        return Stream.of(
            Arguments.of(
                new FormatterComponent(
                    "General information",
                    List.of("Metric", "Meaning"),
                    List.of(
                        "File(s)|'some.log'",
                        "Starting date|-",
                        "Ending date|-",
                        "Number of requests|1",
                        "Average response size|123.0b"
                    )
                ),
                """
                    === General information
                    |================================
                    |               Metric|   Meaning

                    |              File(s)|'some.log'
                    |        Starting date|         -
                    |          Ending date|         -
                    |   Number of requests|         1
                    |Average response size|    123.0b
                    |================================
                    """
            ),

            Arguments.of(
                new FormatterComponent(
                    "Общая информация",
                    List.of("Метрика", "Значение"),
                    List.of(
                        "Файл(-ы)|'лог.log'",
                        "Начальная дата|-",
                        "Конечная дата|-",
                        "Количество запросов|124214",
                        "Средний размер ответа|10293.0b"
                    )
                ),
                """
                    === Общая информация
                    |===============================
                    |              Метрика| Значение

                    |             Файл(-ы)|'лог.log'
                    |       Начальная дата|        -
                    |        Конечная дата|        -
                    |  Количество запросов|   124214
                    |Средний размер ответа| 10293.0b
                    |===============================
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testFormatMethod_shouldReturnFormattedStringInAsciiDocFormat(
        FormatterComponent toFormat,
        String expected) {
        String actual = new ASCIIDocLogStatsFormatter().format(toFormat);
        assertThat(actual).isEqualTo(expected);
    }
}
