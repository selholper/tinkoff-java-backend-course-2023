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

public class TestTask15 {
    private static Stream<Arguments> testMapAnimalsEachTypeTotalWeightFromKToLAge_shouldReturnResult() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        4,
                        100,
                        3,
                        true
                    )
                ),
                5,
                6,
                Map.of()
            ),
            Arguments.of(
                List.of(
                    new Animal(
                        "Booby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        10,
                        123,
                        343,
                        true
                    )
                ),
                0,
                100,
                Map.of(
                    Animal.Type.DOG, 343
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        343,
                        true
                    ),

                    new Animal(
                        "Bobby James Fischer",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        false
                    ),

                    new Animal(
                        "Gosha Rubchinskiy Top",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        8,
                        23,
                        1289,
                        true)
                ),
                0,
                8,
                Map.of(
                    Animal.Type.DOG, 10,
                    Animal.Type.SPIDER, 1289
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testMapAnimalsEachTypeTotalWeightFromKToLAge_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task15.mapAnimalsEachTypeTotalWeightFromKToLAge(listAnimals, 10, 20)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testMapAnimalsEachTypeTotalWeightFromKToLAge_shouldThrowIllegalArgumentException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task15.mapAnimalsEachTypeTotalWeightFromKToLAge(listAnimals, 10, 9)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    void testMapAnimalsEachTypeTotalWeightFromKToLAge_shouldReturnResult(
        List<Animal> listAnimals,
        int k,
        int l,
        Map<Animal.Type, Integer> exceptedMap
        ) {
        assertEquals(Task15.mapAnimalsEachTypeTotalWeightFromKToLAge(listAnimals, k, l), exceptedMap);
    }
}
