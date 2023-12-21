package edu.hw6.Task4;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestTask4 {
    @Test
    void testOutputStreamComposition_shouldWriteMessageToFileUsingOutputStreamComposition(@TempDir Path filePath) {
        Path testFile = filePath.resolve("someFile.txt");
        Task4.outputStreamComposition(testFile);
        assertThat(testFile).hasContent("Programming is learned by writing programs. - Brian Kernighan");
    }
}
