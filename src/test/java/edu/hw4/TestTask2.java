package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {
    private static Stream<Arguments> testSortByWeight_shouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal(
                        "Sharik",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        5,
                        165,
                        12,
                        true
                    )
                ),
                -1
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Bobby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        true
                    ),

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
                        "Gosha",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        3,
                        23,
                        1,
                        true
                    )
                ),
                100
            )
        );
    }

    private static Stream<Arguments> testSortByWeight_shouldReturnSortedListAnimal() {
        return Stream.of(
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
                1,
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
                        true
                    ),

                    new Animal(
                        "Gosha",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        3,
                        23,
                        1,
                        true
                    )
                ),
                2,
                List.of(
                    new Animal(
                        "Bobby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        true
                    ),

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
    void testSortByWeight_shouldThrowNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task2.sortByWeight(listAnimals, -1)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @MethodSource
    void testSortByWeight_shouldThrowIllegalArgumentException(
        List<Animal> listAnimals,
        int k) {
        assertThatThrownBy(
            () -> Task2.sortByWeight(listAnimals, k)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testSortByWeight_shouldReturnEmptyListAnimals(List<Animal> listAnimals) {
        assertThat(Task2.sortByWeight(listAnimals, 0)).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void testSortByWeight_shouldReturnSortedListAnimal(
        List<Animal> listAnimals,
        int k,
        List<Animal> expectedListAnimals) {
        assertEquals(Task2.sortByWeight(listAnimals, k), expectedListAnimals);
    }
}
