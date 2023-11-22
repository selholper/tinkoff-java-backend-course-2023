package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask11 {
    private static Stream<Arguments> testListAnimalsGreaterOneMetreWhichBites_shouldReturnResult() {
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
                List.of()
            ),
            Arguments.of(
                List.of(
                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        3,
                        true
                    )
                ),
                List.of(
                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        3,
                        true
                    )
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
                        3,
                        true
                    ),

                    new Animal(
                        "Bobby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        false
                    ),

                    new Animal(
                        "Gosha",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        8,
                        23,
                        1,
                        true)
                ),
                List.of(
                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        3,
                        true
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testListAnimalsGreaterOneMetreWhichBites_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task11.listAnimalsGreaterOneMetreWhichBites(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testListAnimalsGreaterOneMetreWhichBites_shouldReturnEmptyList(List<Animal> listAnimals) {
        assertEquals(Task11.listAnimalsGreaterOneMetreWhichBites(listAnimals), List.of());
    }

    @ParameterizedTest
    @MethodSource
    void testListAnimalsGreaterOneMetreWhichBites_shouldReturnResult(
        List<Animal> listAnimals,
        List<Animal> exceptedListAnimals) {
        assertEquals(Task11.listAnimalsGreaterOneMetreWhichBites(listAnimals), exceptedListAnimals);
    }
}
