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

public class TestTask12 {
    private static Stream<Arguments> testCountAnimalsWhichWeightExceedsHeight_shouldReturnResult() {
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
                0
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
                    )
                ),
                1
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
                        1289,
                        true)
                ),
                2
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testCountAnimalsWhichWeightExceedsHeight_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task12.countAnimalsWhichWeightExceedsHeight(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testCountAnimalsWhichWeightExceedsHeight_shouldReturnZero(List<Animal> listAnimals) {
        assertEquals(Task12.countAnimalsWhichWeightExceedsHeight(listAnimals), 0);
    }

    @ParameterizedTest
    @MethodSource
    void testCountAnimalsWhichWeightExceedsHeight_shouldReturnResult(
        List<Animal> listAnimals,
        int exceptedValue) {
        assertEquals(Task12.countAnimalsWhichWeightExceedsHeight(listAnimals), exceptedValue);
    }
}
