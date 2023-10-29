package edu.hw3;

import edu.hw3.Task7.TreeMapNullableComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import java.util.TreeMap;
import static java.util.Map.entry;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTask7 {
    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы класса компаратора, обрабатывающего null в TreeMap")
    void testTreeMapNullableComparator_shouldBeContainedInTreeMap(String string) {
        TreeMap<String, String> tree = new TreeMap<>(new TreeMapNullableComparator());

        tree.put(string, "text");
        tree.put("text", "anotherText");
        tree.put("anotherText", "next");

        assertThat(tree.containsKey(string)).isTrue();
        assertThat(tree).contains(entry("text", "anotherText"));
        assertThat(tree).contains(entry("anotherText", "next"));
    }
}
