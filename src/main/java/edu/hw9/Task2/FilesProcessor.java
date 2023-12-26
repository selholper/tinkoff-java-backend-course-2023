package edu.hw9.Task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilesProcessor {
    private static final ForkJoinPool FORK_JOIN_POOL = ForkJoinPool.commonPool();

    @SneakyThrows
    public static List<String> findPathsWithMoreFilesThanGiven(Path root, int number) {
        List<String> result;
        result = FORK_JOIN_POOL.invoke(new FilesCounter(root, number));
        FORK_JOIN_POOL.shutdown();

        return result;
    }

    public static List<String> filterFilesByPredicate(Path root, Predicate<Path> predicate) {
        List<String> result;
        result = FORK_JOIN_POOL.invoke(new FilesFilter(root, predicate));
        FORK_JOIN_POOL.shutdown();

        return result;
    }
}
