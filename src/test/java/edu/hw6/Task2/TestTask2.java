package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    @Test
    void testCloneFile_shouldCloneFileSinceFirstTime(@TempDir Path directoryPath) throws IOException {
        Path file = Files.createFile(directoryPath.resolve("Tinkoff Bank Biggest Secret.txt"));
        Task2.cloneFile(file);
        System.out.println(file);
        assertThat(
            Files.exists(directoryPath.resolve("Tinkoff Bank Biggest Secret - копия.txt"))
        ).isTrue();
    }

    @Test
    void testCloneFile_shouldCloneFileSinceSomeTimes(@TempDir Path directoryPath) throws IOException {
        Path file = Files.createFile(directoryPath.resolve("Tinkoff Bank Biggest Secret.txt"));
        Path copyFile = Files.createFile(directoryPath.resolve("Tinkoff Bank Biggest Secret - копия.txt"));

        Task2.cloneFile(file);

        assertThat(
            Files.exists(directoryPath.resolve("Tinkoff Bank Biggest Secret - копия (2).txt"))
        ).isTrue();

        Task2.cloneFile(copyFile);

        assertThat(
            Files.exists(directoryPath.resolve("Tinkoff Bank Biggest Secret - копия - копия.txt"))
        ).isTrue();
    }
}
