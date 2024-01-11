package edu.project3;

import edu.project3.collector.RequestTypesCollector;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestRequestTypesCollector {
    private static Stream<Arguments> test_shouldReturnRequestsStats() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().request(Request.builder().type("PATCH")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PATCH")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PATCH")
                            .build()).timeLocal(OffsetDateTime.now()).build()
                    )
                ),
                List.of("PATCH|3")
            ),

            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().request(Request.builder().type("GET")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("GET")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PATCH")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PUT")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("GET")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("DELETE")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("DELETE")
                            .build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PATCH")
                            .build()).timeLocal(OffsetDateTime.now()).build()
                    )
                ), List.of("GET|3", "PUT|1", "DELETE|2", "PATCH|2")
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RequestTypesCollector(LogFilter.link(
            new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
