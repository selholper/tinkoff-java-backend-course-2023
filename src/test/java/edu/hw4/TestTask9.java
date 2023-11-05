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

public class TestTask9 {
    private static Stream<Arguments> testAnimalsPawSum_shouldReturnResult() {
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
                4
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
                        true)
                ),
                16
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "DORI",
                        Animal.Type.FISH,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        3,
                        true
                    ),

                    new Animal(
                        "Golub",
                        Animal.Type.BIRD,
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
                        true)
                ),
                10
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testAnimalsPawSum_shouldReturnNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task9.animalsPawSum(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testAnimalsPawSum_shouldReturnZeroForEmptyList(List<Animal> listAnimals) {
        assertEquals(Task9.animalsPawSum(listAnimals), 0);
    }

    @ParameterizedTest
    @MethodSource
    void testAnimalsPawSum_shouldReturnResult(List<Animal> listAnimals, int pawSum) {
        assertEquals(Task9.animalsPawSum(listAnimals), pawSum);
    }
}
