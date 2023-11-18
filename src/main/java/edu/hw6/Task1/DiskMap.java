package edu.hw6.Task1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path filePath;

    public DiskMap(Path filePath) {
        this.filePath = filePath;
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                LOGGER.trace("Created a file along the path: {}", filePath.toString());
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public Map<String, String> readFromFile(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            Map<String, String> parsedMap = lines
                .stream()
                .map(entry -> entry.split(":"))
                .collect(
                    Collectors.toMap(
                        entry -> entry[0],
                        entry -> entry[1]
                    )
                );
            LOGGER.trace("Map parsed from path \"{}\": {}", filePath.toString(), parsedMap);

            return parsedMap;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException("Each line of file must correspond next regex: "
                + "«^.*:.+$»");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void writeToFile(Path filePath, Map<String, String> map) {
        List<String> lines = map.entrySet()
            .stream()
            .map(entry -> entry.getKey() + ":" + entry.getValue())
            .toList();

        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        LOGGER.trace("Lines write to the path \"{}\": {}", filePath.toString(), lines);
    }

    @Override
    public int size() {
        return readFromFile(filePath).size();
    }

    @Override
    public boolean isEmpty() {
        return readFromFile(filePath).isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return readFromFile(filePath).containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return readFromFile(filePath).containsValue(value);
    }

    @Override
    public String get(Object key) {
        return readFromFile(filePath).get(key);
    }

    @Override
    public String put(String key, String value) {
        Map<String, String> map = readFromFile(filePath);
        String result = map.put(key, value);
        writeToFile(filePath, map);

        return result;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> map = readFromFile(filePath);
        String result = map.remove(key);
        writeToFile(filePath, map);

        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> anotherMap) {
        Map<String, String> map = readFromFile(filePath);
        map.putAll(anotherMap);
        writeToFile(filePath, map);
    }

    @Override
    public void clear() {
        writeToFile(filePath, Map.of());
    }

    @Override
    @NotNull
    public Set<String> keySet() {
        return readFromFile(filePath).keySet();
    }

    @Override
    @NotNull
    public Collection<String> values() {
        return readFromFile(filePath).values();
    }

    @Override
    @NotNull
    public Set<Entry<String, String>> entrySet() {
        return readFromFile(filePath).entrySet();
    }
}
