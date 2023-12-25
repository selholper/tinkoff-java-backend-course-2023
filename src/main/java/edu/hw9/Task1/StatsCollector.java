package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;

public final class StatsCollector {
    private final List<Future<Metric>> metricFutures;
    private final ExecutorService executorService;

    public StatsCollector(int threadNumber) {
        metricFutures = new CopyOnWriteArrayList<>();
        executorService = Executors.newFixedThreadPool(threadNumber);
    }

    public void push(String metricName, double[] numbers) {
        metricFutures.add(executorService.submit(() -> collect(metricName, numbers)));
    }

    @SneakyThrows
    public List<Metric> stats() {
        List<Metric> metrics = new ArrayList<>();
        for (var metricFuture : metricFutures) {
            metrics.add(metricFuture.get());
        }

        return metrics;
    }

    private Metric collect(String metricName, double[] numbers) {
        return new Metric(
            metricName,
            Arrays.stream(numbers).sum(),
            Arrays.stream(numbers).average()
                .orElseThrow(() -> new RuntimeException("There are no numbers in the metric!")),
            Arrays.stream(numbers).min().orElseThrow(),
            Arrays.stream(numbers).max().orElseThrow()
        );
    }
}
