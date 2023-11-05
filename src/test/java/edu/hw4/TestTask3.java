package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {
    private static Stream<Arguments> testHowManyAnimalsOfEachType_shouldReturnMapOfAnimalsFrequency() {
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
                Map.of(
                    Animal.Type.CAT, 1
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
                Map.of(
                    Animal.Type.CAT, 1,
                    Animal.Type.DOG, 1,
                    Animal.Type.SPIDER, 1
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testHowManyAnimalsOfEachType_shouldThrowNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task3.howManyAnimalsOfEachType(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testHowManyAnimalsOfEachType_shouldReturnEmptyMapOfAnimalsFrequency(
        List<Animal> listAnimals
    ) {
        assertEquals(Task3.howManyAnimalsOfEachType(listAnimals), Map.of());
    }

    @ParameterizedTest
    @MethodSource
    void testHowManyAnimalsOfEachType_shouldReturnMapOfAnimalsFrequency(
        List<Animal> listAnimals,
        Map<Animal.Type, Integer> expectedResult
    ) {
        assertEquals(Task3.howManyAnimalsOfEachType(listAnimals), expectedResult);
    }
}
