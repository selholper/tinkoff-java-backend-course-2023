package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask8 {
    private static Stream<Arguments> testHeaviestAnimalAmongAnimalsUnderGivenHeight_shouldReturnResult() {
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
                124,
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
                100,
                new Animal(
                    "Gosha",
                    Animal.Type.SPIDER,
                    Animal.Sex.MALE,
                    5,
                    23,
                    1,
                    true)
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
                10,
                null
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testHeaviestAnimalAmongAnimalsUnderGivenHeight_shouldThrowNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task8.heaviestAnimalAmongAnimalsUnderGivenHeight(listAnimals, 10)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testHeaviestAnimalAmongAnimalsUnderGivenHeight_shouldReturnIllegalArgumentException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task8.heaviestAnimalAmongAnimalsUnderGivenHeight(listAnimals, 0)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    void testHeaviestAnimalAmongAnimalsUnderGivenHeight_shouldReturnResult(
        List<Animal> listAnimals,
        int k,
        Animal optionalAnimal
    ) {
        assertEquals(
            Task8.heaviestAnimalAmongAnimalsUnderGivenHeight(listAnimals, k),
            Optional.ofNullable(optionalAnimal)
        );
    }
}
