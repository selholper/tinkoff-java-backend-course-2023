package edu.project3;

import edu.project3.formatter.MarkdownLogStatsFormatter;
import edu.project3.model.FormatterComponent;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMarkdownLogStatsFormatter {
    private static Stream<Arguments> testFormatMethod_shouldReturnFormattedStringInMarkdownFormat() {
        return Stream.of(
            Arguments.of(
                new FormatterComponent(
                    "Общая информация",
                    List.of("Метрика", "Значение"),
                    List.of(
                        "Файл(-ы)|'лог).log'",
                        "Начальная дата|-",
                        "Конечная дата|-",
                        "Количество запросов|2",
                        "Средний размер ответа|300.0b"
                    )
                ),
                """
                    ### Общая информация
                    |              Метрика|  Значение|
                    |:-------------------:|:--------:|
                    |             Файл(-ы)|'лог).log'|
                    |       Начальная дата|         -|
                    |        Конечная дата|         -|
                    |  Количество запросов|         2|
                    |Средний размер ответа|    300.0b|
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testFormatMethod_shouldReturnFormattedStringInMarkdownFormat(
        FormatterComponent toFormat,
        String expected) {
        String actual = new MarkdownLogStatsFormatter().format(toFormat);
        assertThat(actual).isEqualTo(expected);
    }
}
