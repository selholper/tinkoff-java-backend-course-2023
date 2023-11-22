package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask20 {
    private static Stream<Arguments> testReadableValidateListAnimals_shouldReturnResult() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal(
                        "Dori",
                        null,
                        null,
                        -1,
                        -100,
                        -3,
                        true
                    )
                ),
                Map.of(
                    "Dori", "Type, Height, Weight, Sex, Age"
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testReadableValidateListAnimals_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task20.readableValidateListAnimals(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testReadableValidateListAnimals_shouldReturnEmptyMap(
        List<Animal> listAnimals) {
        assertEquals(Task20.readableValidateListAnimals(listAnimals), Map.of());
    }

    @ParameterizedTest
    @MethodSource
    void testReadableValidateListAnimals_shouldReturnResult(
        List<Animal> listAnimals,
        Map<String, String> exceptedMap
    ) {
        assertEquals(Task20.readableValidateListAnimals(listAnimals), exceptedMap);
    }
}
