package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public final class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> calculations;
    private final ThreadWorker[] threads;
    private final AtomicBoolean isCalculated;

    private FixedThreadPool(ThreadWorker[] threads) {
        this.threads = threads;
        this.calculations = new LinkedBlockingQueue<>();
        this.isCalculated = new AtomicBoolean(false);
        start();
    }

    public static FixedThreadPool create(int numberOfThreads) {
        if (numberOfThreads < 1) {
            throw new IllegalArgumentException("Invalid thread number!");
        }

        return new FixedThreadPool(new ThreadWorker[numberOfThreads]);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadWorker();
            threads[i].start();
        }
    }

    @Override
    @SneakyThrows
    public void execute(Runnable runnable) {
        if (!isCalculated.get()) {
            calculations.put(runnable);
        }
    }

    @Override
    public void close() {
        isCalculated.set(true);
    }

    private final class ThreadWorker extends Thread {
        @Override
        @SneakyThrows
        public void run() {
            while (!isCalculated.get() || !calculations.isEmpty()) {
                Runnable runnable;
                while ((runnable = calculations.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
