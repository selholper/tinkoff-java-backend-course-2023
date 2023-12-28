package edu.hw8.Task3;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class MultiThreadPasswordRetractor extends AbstractPasswordRetractor {
    private final ExecutorService executorService;
    private final int threadsNumber;


    public MultiThreadPasswordRetractor(Map<String, String> users, int threadNumber) {
        super(new ConcurrentHashMap<>(users));
        this.executorService = Executors.newFixedThreadPool(threadNumber);
        this.threadsNumber = threadNumber;
    }

    @SneakyThrows
    @Override
    public List<User> decode() {
        int shift = -1;
        int alphabetPerThread = ALPHABET.length / threadsNumber;
        for (int thread = 0; thread < threadsNumber - 1; ++thread) {
            final int start = shift;
            executorService.execute(
                () -> decodeInRange(start, start + alphabetPerThread)
            );
            shift += alphabetPerThread;
        }
        final int start = shift;
        executorService.execute(() -> decodeInRange(start, ALPHABET.length));
        executorService.shutdown();
        boolean isTerminated = executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        return isTerminated ? decodedUsers : List.of();
    }
}
