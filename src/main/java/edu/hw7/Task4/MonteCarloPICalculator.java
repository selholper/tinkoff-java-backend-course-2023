package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public final class MonteCarloPICalculator {
    private static final double R = 0.5;
    private static final double K = 4.0;

    private MonteCarloPICalculator() {
    }

    public static double calculateInSingleThreadMode(long totalCount)  {
        Random random = new Random();

        long circleCount = 0;
        for (int i = 0; i < totalCount; ++i) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (isPointInsideCircle(x, y)) {
                ++circleCount;
            }
        }

        return K * circleCount / totalCount;
    }

    @SneakyThrows
    public static double calculateInMultiThreadMode(long totalCount) {
        // We get the number of available cores (threads) to ensure performance.
        int availableCoresNumber = Runtime.getRuntime().availableProcessors();
        long circleCount = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(availableCoresNumber);
        List<Future<Long>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(availableCoresNumber);

        for (int thread = 0; thread < availableCoresNumber; ++thread) {
            Future<Long> threadExecutorService;
            threadExecutorService = executorService.submit(() -> {
                long threadCircleCount = 0;
                for (int i = 0; i < totalCount / availableCoresNumber; ++i) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (isPointInsideCircle(x, y)) {
                        ++threadCircleCount;
                    }
                }
                countDownLatch.countDown();

                return threadCircleCount;
            });
            futures.add(threadExecutorService);
        }

        countDownLatch.await();
        for (int threadNumber = 0; threadNumber < availableCoresNumber; ++threadNumber) {
            circleCount += futures.get(threadNumber).get();
        }
        executorService.shutdown();

        return K * circleCount / totalCount;
    }

    private static boolean isPointInsideCircle(double x, double y) {
        return (R - x) * (R - x) + (R - y) * (R - y) < R * R;
    }
}
