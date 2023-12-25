package edu.hw9.Task1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class TestStatsCollector {
    @SneakyThrows
    @Test
    public void testStatsMethod_shouldReturnAllStatsForCorrectMetrics() {
        StatsCollector statsCollector = new StatsCollector(8);
        statsCollector.push("metric0", new double[]{0.1, 0.05, 1.4, 5.2, 0.3});
        statsCollector.push("metric1", new double[]{1, 2, 3, 4, 5});
        statsCollector.push("metric2", new double[]{1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7});

        var stats = statsCollector.stats();
        Assertions.assertThat(stats).containsExactlyInAnyOrder(
            new Metric("metric0", 7.05, 1.41, 0.05, 5.2),
            new Metric("metric1", 15.0, 3.0, 1.0, 5.0),
            new Metric("metric2", 30.8, 4.4, 1.1, 7.7)
        );
    }
}
