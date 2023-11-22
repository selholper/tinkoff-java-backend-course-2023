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

public class TestTask5 {
    private static Stream<Arguments> testMostCommonSexOfAnimals_shouldReturnResult() {
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
                Animal.Sex.FEMALE
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
                        "Georgy",
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
                Animal.Sex.MALE
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testMostCommonSexOfAnimals_shouldThrowNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task5.mostCommonSexOfAnimals(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testMostCommonSexOfAnimals_shouldReturnIllegalArgumentException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task5.mostCommonSexOfAnimals(listAnimals)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    void testMostCommonSexOfAnimals_shouldReturnResult(
        List<Animal> listAnimals,
        Animal.Sex sex) {
        assertEquals(Task5.mostCommonSexOfAnimals(listAnimals), sex);
    }
}
