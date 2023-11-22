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

public class TestTask1 {
    private static Stream<Arguments> testSortByHeight_shouldReturnSortedListAnimals() {
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
                        true),

                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        3,
                        true)
                ),

                List.of(
                    new Animal(
                        "Gosha",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        3,
                        23,
                        1,
                        true),

                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        3,
                        true),

                    new Animal(
                        "Bobby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        true
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testSortByHeight_shouldThrowNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task1.sortByHeight(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testSortByHeight_shouldReturnEmptyListAnimals(List<Animal> listAnimals) {
        assertThat(Task1.sortByHeight(listAnimals)).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void testSortByHeight_shouldReturnSortedListAnimals(
        List<Animal> listAnimals,
        List<Animal> expectedListAnimal) {
        assertEquals(Task1.sortByHeight(listAnimals), expectedListAnimal);
    }
}
