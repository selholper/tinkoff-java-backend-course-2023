package edu.project3;

import edu.project3.collector.RequestsCollector;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TestRequestsCollector {
    private static Stream<Arguments> test_shouldReturnRequestsStats() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test")),
                    List.of(
                        NginxLog.builder().response(Response.builder().statusCode(422).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(422).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(422).build()).timeLocal(
                            OffsetDateTime.now()).build()
                    )
                ),
                List.of("422|Unprocessable Entity|3")
            ),

            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test")),
                    List.of(
                        NginxLog.builder().response(Response.builder().statusCode(200).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(200).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(200).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(404).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(404).build()).timeLocal(
                            OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(302).build()).timeLocal(
                            OffsetDateTime.now()).build()
                    )
                ), List.of("200|OK|3", "404|Not Found|2", "302|Found|1")
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RequestsCollector(
            LogFilter.link(new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
