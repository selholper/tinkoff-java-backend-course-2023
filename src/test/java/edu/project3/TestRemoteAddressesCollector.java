package edu.project3;

import edu.project3.collector.RemoteAddressesCollector;
import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.model.FormatterComponent;
import edu.project3.model.LogData;
import edu.project3.model.LogSourceWrapper;
import edu.project3.model.NginxLog;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestRemoteAddressesCollector {
    private static Stream<Arguments> test_shouldReturnRequestsStats() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().remoteAddress("192.168.0.1").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.168.0.1").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.168.0.1").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.168.0.1").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.168.0.1").timeLocal(OffsetDateTime.now()).build()
                    )
                ), List.of("192.168.0.1|5")
            ),

            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.253.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.253.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.73.252.145").timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().remoteAddress("192.168.0.1").timeLocal(OffsetDateTime.now()).build()
                    )
                ), List.of("192.73.252.145|4", "192.73.253.145|2", "192.168.0.1|1")
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RemoteAddressesCollector(LogFilter.link(
            new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
