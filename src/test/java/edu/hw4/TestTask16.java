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

public class TestTask16 {
    private static Stream<Arguments> testListAnimalsSortedByTypeSexName_shouldReturnResult() {
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
                )
            ),
            Arguments.of(
                List.of(
                    new Animal(
                        "Charlie",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        10,
                        123,
                        343,
                        true
                    ),

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
                List.of(
                    new Animal(
                        "Booby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        10,
                        123,
                        343,
                        true
                    ),

                    new Animal(
                        "Charlie",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        10,
                        123,
                        343,
                        true
                    )
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Bobby James Fischer",
                        Animal.Type.CAT,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        false
                    ),

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
                        "Gosha Rubchinskiy Top",
                        Animal.Type.CAT,
                        Animal.Sex.MALE,
                        8,
                        23,
                        1289,
                        true)
                ),

                List.of(
                    new Animal(
                        "Bobby James Fischer",
                        Animal.Type.CAT,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        false
                    ),

                    new Animal(
                        "Gosha Rubchinskiy Top",
                        Animal.Type.CAT,
                        Animal.Sex.MALE,
                        8,
                        23,
                        1289,
                        true),

                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        343,
                        true
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testListAnimalsSortedByTypeSexName_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task16.listAnimalsSortedByTypeSexName(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testListAnimalsSortedByTypeSexName_shouldReturnEmptyList(
        List<Animal> listAnimals) {
        assertEquals(Task16.listAnimalsSortedByTypeSexName(listAnimals), List.of());
    }

    @ParameterizedTest
    @MethodSource
    void testListAnimalsSortedByTypeSexName_shouldReturnResult(
        List<Animal> listAnimals,
        List<Animal> exceptedListAnimal
    ) {
        assertEquals(Task16.listAnimalsSortedByTypeSexName(listAnimals), exceptedListAnimal);
    }
}
