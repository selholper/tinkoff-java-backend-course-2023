package edu.hw6.Task1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {
    private static final String INPUT_PATH = "src/test/java/edu/hw6/Task1/IOFiles/input.txt";
    private static final String OUTPUT_PATH = "src/test/java/edu/hw6/Task1/IOFiles/output.txt";
    @ParameterizedTest
    @ValueSource(
        strings = {"/NO_SUCH_CATALOGUE/123", "/src/NO_SUCH_CATALOGUE/1.txt", "!^&#@%\\12/aslkdjf.1.txt"}
    )
    void testDiskMapClass_shouldThrowRuntimeExceptionForNonExistentPath(String nonExistentPath) {
        assertThatThrownBy(
            () -> new DiskMap(Paths.get(nonExistentPath))
        ).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {"/NO_SUCH_CATALOGUE/123", "/src/NO_SUCH_CATALOGUE/1.txt", "!^&#@%\\12/aslkdjf.1.txt"}
    )
    void testDiskMapClassReadFromFileMethod_shouldReturnRuntimeExceptionForNonExistentPath(String nonExistentPath) {
        try {
            Path inputPath = Paths.get(INPUT_PATH);
            if (!Files.exists(inputPath)) {
                Files.createFile(inputPath);
            }
            assertThatThrownBy(
                () -> new DiskMap(inputPath)
                    .readFromFile(Paths.get(nonExistentPath))
            ).isInstanceOf(RuntimeException.class);
            Files.delete(inputPath);
        } catch (IOException ignored) {
        }
    }

    @ParameterizedTest
    @ValueSource(
        strings = {"/NO_SUCH_CATALOGUE/123", "/src/NO_SUCH_CATALOGUE/1.txt", "!^&#@%\\12/aslkdjf.1.txt"}
    )
    void testDiskMapClassWriteToFileMethod_shouldReturnRuntimeExceptionForNonExistentPath(String nonExistentPath) {
        try {
            Path outputPath = Paths.get(OUTPUT_PATH);
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath);
            }
            Files.createFile(outputPath);
            assertThatThrownBy(
                () -> new DiskMap(outputPath)
                    .writeToFile(Paths.get(nonExistentPath))
            ).isInstanceOf(RuntimeException.class);
            Files.delete(outputPath);
        } catch (IOException ignored) {
        }
    }

    private static Stream<Arguments> testDiskMapClassReadFromFileMethod_shouldReturnArrayIndexOutOfBoundsException() {
        return Stream.of(
            Arguments.of(
                List.of("1:")
            ),

            Arguments.of(
                List.of(":")
            ),

            Arguments.of(
                List.of("12:12", "126735162735")
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testDiskMapClassReadFromFileMethod_shouldReturnArrayIndexOutOfBoundsException(List<String> list) {
        try {
            Path inputPath = Paths.get(INPUT_PATH);

            if(!Files.exists(inputPath)) {
                Files.createFile(inputPath);
            }

            Files.write(inputPath, list, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            assertThatThrownBy(
                () -> new DiskMap(inputPath).readFromFile(inputPath)
            ).isInstanceOf(IndexOutOfBoundsException.class);

            Files.delete(inputPath);
        } catch (IOException ignored) {
        }
    }

    private static Stream<Arguments> testDiskMapClass_shouldWorkCorrectlyForPutOperation() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "1:1",
                    "12:0"
                ),
                "2", "2",
                List.of(
                    "12:0",
                    "1:1",
                    "2:2"
                )
            ),

            Arguments.of(
                List.of(
                    "1:1",
                    "2:2",
                    "asdlfhsadlf:12341234"
                ),
                "--", "&(!",
                List.of(
                    "--:&(!",
                    "1:1",
                    "2:2",
                    "asdlfhsadlf:12341234"
                )
            ),

            Arguments.of(
                List.of(
                    "10:10",
                    "129037:12312",
                    "1:10",
                    ",.:123",
                    "+_:()"
                ),
                "$", "#",
                List.of(
                    "$:#",
                    "+_:()",
                    ",.:123",
                    "10:10",
                    "129037:12312",
                    "1:10"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testDiskMapClass_shouldWorkCorrectlyForPutOperation(List<String> list,
        String key, String value, List<String> exceptedRes) {
        try {
            Path inputPath = Paths.get(INPUT_PATH);
            Path outputPath = Paths.get(OUTPUT_PATH);

            if (!Files.exists(inputPath)) {
                Files.createFile(inputPath);
            }
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath);
            }

            Files.write(inputPath, list, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            DiskMap diskMap = new DiskMap(inputPath);
            diskMap.readFromFile(inputPath);
            diskMap.put(key, value);
            diskMap.writeToFile(outputPath);

            List<String> res = Files.readAllLines(outputPath, StandardCharsets.UTF_8);

            res.sort(Comparator.naturalOrder());
            assertEquals(res, exceptedRes);

            Files.delete(inputPath);
            Files.delete(outputPath);
        } catch (IOException ignored) {
        }
    }

    @Test
    void testDiskMapMethods_shouldReturnCorrectResult() {
        DiskMap diskMap = new DiskMap();

        assertThat(diskMap.isEmpty()).isTrue();

        diskMap.put("1", "1");

        assertThat(diskMap.size()).isEqualTo(1);
        assertThat(diskMap.containsKey("1")).isTrue();
        assertThat(diskMap.containsValue("1")).isTrue();
        assertThat(diskMap.get("1")).isEqualTo("1");
        assertThat(diskMap.put("1", "2")).isEqualTo("1");
        assertThat(diskMap.remove("1")).isEqualTo("2");

        diskMap.putAll(Map.of("1", "1", "2", "2", "a", "b"));

        assertThat(diskMap.keySet()).isEqualTo(Set.of("1", "2", "a"));
        assertThat(diskMap.values().stream().sorted().toList()).isEqualTo(List.of("1", "2", "b"));
    }
}
