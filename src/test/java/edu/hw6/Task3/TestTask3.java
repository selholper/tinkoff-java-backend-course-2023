package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestTask3 {
    @TempDir
    private Path filePath;
    private List<Path> pathList;

    @BeforeEach
    void initializeFiles() throws IOException {
        pathList = new ArrayList<>();

        pathList.add(Files.writeString(Files.createTempFile(filePath, "text", ".txt"), "some text here"));
        pathList.add(Files.createTempFile(filePath, "sakldjfh", ".txt"));
        pathList.add(Files.createTempFile(filePath, "pattern1", ".txt"));
        pathList.add(Files.createTempFile(filePath, "pattern28721", ".txt"));
        pathList.add(Files.createTempFile(filePath, "pattern0", ".txt"));
        pathList.add(Files.createTempFile(filePath, "pattern4123", ".txt"));
        pathList.add(Files.createTempFile(filePath, "pattern1031", ".txt"));
        pathList.add(Files.write(
            Files.createTempFile(filePath, "filename", ".png"),
            new byte[] {(byte)0x89, 'P', 'N', 'G'}
        ));
    }

    @Test
    void testRegexContains_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = Task3.regexContains("pattern\\d+");
        List<Path> pathArrayList = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(filePath, filter)) {
            path.forEach(pathArrayList::add);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        assertThat(pathArrayList).containsExactlyInAnyOrder(
            pathList.get(2),
            pathList.get(3),
            pathList.get(4),
            pathList.get(5),
            pathList.get(6)
        );
    }

    @Test
    void testGlobMatches_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = Task3.globMatches("*.png");
        List<Path> pathArrayList = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(filePath, filter)) {
            path.forEach(pathArrayList::add);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        assertThat(pathArrayList).containsExactlyInAnyOrder(pathList.get(7));
    }

    @Test
    void testLargerThan_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = Task3.largerThan(10);
        List<Path> pathArrayList = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(filePath, filter)) {
            path.forEach(pathArrayList::add);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        assertThat(pathArrayList).containsExactlyInAnyOrder(pathList.get(0));
    }

    @Test
    void magicNumber_shouldReturnFilteredPaths() {
        DirectoryStream.Filter<Path> filter = Task3.magicNumber((byte)0x89, (byte) 'P', (byte) 'N', (byte) 'G');
        List<Path> pathArrayList = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(filePath, filter)) {
            path.forEach(pathArrayList::add);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        assertThat(pathArrayList).containsExactlyInAnyOrder(pathList.get(7));
    }

    @Test
    void testFilter_shouldReturnFilteredByTwoFiltersPaths() {
        DirectoryStream.Filter<Path> filter = Task3
            .magicNumber((byte)0x89, (byte)'P', (byte)'N', (byte)'G')
            .and(Task3.largerThan(1));
        List<Path> actual = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(filePath, filter)) {
            path.forEach(actual::add);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        assertThat(actual).containsExactlyInAnyOrder(pathList.get(7));
    }
}
