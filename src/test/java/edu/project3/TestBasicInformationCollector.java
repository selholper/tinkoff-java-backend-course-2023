package edu.project3;

import edu.project3.collector.BasicInformationCollector;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestBasicInformationCollector {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    private static Stream<Arguments> test_shouldReturnRequestsStats() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test.log")),
                    List.of(
                        NginxLog.builder().response(Response.builder().bodyBytesSend(200).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(400).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(900).build()).timeLocal(
                            OffsetDateTime.now()).build()
                    )
                ),
                List.of(
                    "Файл(-ы)|'test.log'",
                    "Начальная дата|-",
                    "Конечная дата|-",
                    "Количество запросов|3",
                    "Средний размер ответа|500.0b"
                )
            ),
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test1.log", "test2.log"), OffsetDateTime.now(), OffsetDateTime.now()),
                    List.of(
                        NginxLog.builder().response(Response.builder().bodyBytesSend(123).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(1).build()).timeLocal(
                            OffsetDateTime.now()).build()
                    )
                ),
                List.of(
                    "Файл(-ы)|'test1.log''test2.log'",
                    "Начальная дата|" + DATE_FORMATTER.format(OffsetDateTime.now()),
                    "Конечная дата|" + DATE_FORMATTER.format(OffsetDateTime.now()),
                    "Количество запросов|2",
                    "Средний размер ответа|62.0b"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_shouldReturnRequestsStats(
        LogSourceWrapper testLogs,
        List<String> expectedLines) {
        FormatterComponent actual = new BasicInformationCollector(LogFilter.link(
            new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
