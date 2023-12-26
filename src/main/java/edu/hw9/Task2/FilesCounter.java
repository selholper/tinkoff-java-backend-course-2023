package edu.hw9.Task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;

public class FilesCounter extends RecursiveTask<List<String>> {

    private final Path directory;
    private final int minFiles;

    public FilesCounter(Path directory, int minFiles) {
        this.directory = directory;
        this.minFiles = minFiles;
    }

    @Override
    @SneakyThrows
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        List<FilesCounter> forks = new ArrayList<>();
        int filesCounter = 0;

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    ++filesCounter;
                } else if (Files.isDirectory(path)) {
                    FilesCounter nextTask = new FilesCounter(path, minFiles);
                    nextTask.fork();
                    forks.add(nextTask);
                }
            }

            if (filesCounter > minFiles) {
                result.add(directory.toString());
            }
        }

        for (FilesCounter task : forks) {
            result.addAll(task.join());
        }

        return result;
    }
}
