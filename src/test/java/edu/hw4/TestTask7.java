package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask7 {
    private static Stream<Arguments> testKthMostOldAnimal_shouldReturnResult() {
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
                        5,
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
                2,
                new Animal(
                    "Gosha",
                    Animal.Type.SPIDER,
                    Animal.Sex.MALE,
                    5,
                    23,
                    1,
                    true)
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testKthMostOldAnimal_shouldThrowNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task7.kthMostOldAnimal(listAnimals, 10)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testKthMostOldAnimal_shouldReturnIllegalArgumentException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task7.kthMostOldAnimal(listAnimals, 0)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    void testKthMostOldAnimal_shouldReturnResult(List<Animal> listAnimals, int k, Animal animal) {
        assertEquals(Task7.kthMostOldAnimal(listAnimals, k), animal);
    }
}
