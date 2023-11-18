package edu.hw6.Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask1 {

    @TempDir(cleanup = CleanupMode.ALWAYS)
    private Path directoryPath;
    private Path filePath;
    private DiskMap diskMap;

    @BeforeEach
    void initializeFile() {
        filePath = directoryPath.resolve("dictionary.txt");
        diskMap = new DiskMap(filePath);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {"/NO_SUCH_CATALOGUE/123", "/src/NO_SUCH_CATALOGUE/1.txt", "!^&#@%\\12/aslkdjf.1.txt"}
    )
    void testDiskMapClass_shouldThrowRuntimeExceptionForNonExistentPath(String path) {
        assertThatThrownBy(
            () -> new DiskMap(Paths.get(path))
        ).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {"/NO_SUCH_CATALOGUE/123", "/src/NO_SUCH_CATALOGUE/1.txt", "!^&#@%\\12/aslkdjf.1.txt"}
    )
    void testDiskMapClassReadFromFileMethod_shouldThrowRuntimeExceptionForNonExistentPath(String path) {
        assertThatThrownBy(
            () -> diskMap.readFromFile(Paths.get(path))
        ).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testDiskMapClassReadFromFileMethod_shouldThrowArrayIndexOutOfBoundsException() throws IOException {
        Files.write(filePath, List.of("1:"));

        assertThatThrownBy(
            () -> diskMap.readFromFile(filePath)
        ).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {"/NO_SUCH_CATALOGUE/123", "/src/NO_SUCH_CATALOGUE/1.txt", "!^&#@%\\12/aslkdjf.1.txt"}
    )
    void testDiskMapClassWriteToFileMethod_shouldThrowRuntimeExceptionForNonExistentPath(String path) {
        assertThatThrownBy(
            () -> diskMap.writeToFile(Paths.get(path), Map.of())
        ).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testDiskMapClassSizeMethod_shouldReturnResult() {
        diskMap.put("1", "1");
        Assertions.assertEquals(diskMap.size(), 1);

        diskMap.put("2", "1");
        Assertions.assertEquals(diskMap.size(), 2);

        diskMap.put("(", ")");
        Assertions.assertEquals(diskMap.size(), 3);

        diskMap.remove("(");
        Assertions.assertEquals(diskMap.size(), 2);
    }

    @Test
    void testDiskMapClassIsEmptyMethod_shouldReturnResult() {
        Assertions.assertTrue(diskMap.isEmpty());

        diskMap.put("1", "1");
        Assertions.assertFalse(diskMap.isEmpty());
    }

    @Test
    void testDiskMapClassContainsKeyMethod_shouldReturnResult() {
        Assertions.assertFalse(diskMap.containsKey("1"));

        diskMap.put("1", "1");
        Assertions.assertTrue(diskMap.containsKey("1"));
    }

    @Test
    void testDiskMapClassContainValueMethod_shouldReturnResult() {
        Assertions.assertFalse(diskMap.containsValue("1"));

        diskMap.put("2", "1");
        Assertions.assertTrue(diskMap.containsValue("1"));
    }

    @Test
    void testDiskMapClassGetMethod_shouldReturnResult() {
        Assertions.assertNull(diskMap.get("1"));

        diskMap.put("1", "1");

        Assertions.assertEquals(diskMap.get("1"), "1");
    }

    @Test
    void testDiskMapClassPutMethod_shouldReturnResult() {
        Assertions.assertNull(diskMap.put("1", "1"));
        Assertions.assertEquals(diskMap.put("1", "2"), "1");
    }

    @Test
    void testDiskMapClassRemoveMethod_shouldReturnResult() {
        Assertions.assertNull(diskMap.remove("1"));
        diskMap.put("1", "abc");
        Assertions.assertEquals(diskMap.remove("1"), "abc");
    }

    @Test
    void testDiskMapClassPutAllMethod_shouldReturnResult() {
        diskMap.put("1", "1");
        diskMap.put("3", "abc");

        Map<String, String> map = Map.of("1", "2", "2", "3");

        diskMap.putAll(map);

        Assertions.assertEquals(diskMap.entrySet(),
            Map.of("1", "2", "2", "3", "3", "abc").entrySet());
    }

    @Test
    void testDiskMapClassClearMethod_shouldClearMap() {
        diskMap.put("1", "1");
        diskMap.put("a", "b");
        diskMap.put("1203", "k");

        diskMap.clear();

        assertThat(filePath).isEmptyFile();
    }

    @Test
    void testDiskMapClassKeySetMethod_shouldReturnResult() {
        diskMap.put("1", "1");
        diskMap.put("a", "b");
        diskMap.put("1203", "k");

        assertThat(diskMap.keySet()).containsExactlyInAnyOrder("1", "a", "1203");
    }

    @Test
    void testDiskMapClassValuesMethod_shouldReturnResult() {
        diskMap.put("1", "1");
        diskMap.put("a", "b");
        diskMap.put("1203", "k");

        assertThat(diskMap.values()).containsExactlyInAnyOrder("1", "b", "k");
    }

    @Test
    void testDiskMapClassEntrySetClass_shouldReturnResult() {
        diskMap.put("1", "1");
        diskMap.put("a", "b");
        diskMap.put("1203", "k");

        Set<Map.Entry<String, String>> res = diskMap.entrySet();

        assertThat(res).containsExactlyInAnyOrder(
            entry("1", "1"),
            entry("a", "b"),
            entry("1203", "k")
        );
    }
}
