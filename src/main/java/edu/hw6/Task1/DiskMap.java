package edu.hw6.Task1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final HashMap<String, String> map;

    public DiskMap(Path filePath) {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            LOGGER.trace("Create a new file on the path: {}", filePath.toString());
        }
        map = new HashMap<>();
    }

    public void readFromFile(Path filePath) {
        if (!Files.exists(filePath)) {
            throw new RuntimeException("The file at the specified path does not exist");
        }

        try {
            clear();
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            Map<String, String> parsedMap = lines.stream()
                .map(entry -> entry.split(":"))
                .collect(
                    Collectors.toMap(
                        entry -> entry[0],
                        entry -> entry[1]
                    )
                );
            LOGGER.trace("Map parsed from path \"{}\": {}", filePath.toString(), parsedMap);

            putAll(parsedMap);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException("Each line of file must correspond next regex: "
                + "«^.*:.+$»");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void writeToFile(Path filePath) {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            LOGGER.trace("Create a new file to write on the path: {}", filePath.toString());
        }

        List<String> lines = entrySet()
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
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> anotherMap) {
        map.putAll(anotherMap);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    @NotNull
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    @NotNull
    public Collection<String> values() {
        return map.values();
    }

    @Override
    @NotNull
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
