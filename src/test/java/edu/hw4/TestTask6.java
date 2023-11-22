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

public class TestTask6 {
    private static Stream<Arguments> testHeaviestAnimalOfEachType_shouldReturnResult() {
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
                    Animal.Type.CAT, new Animal(
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
                        "Georgy",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        23,
                        1123,
                        true)
                ),

                Map.of(
                    Animal.Type.DOG, new Animal(
                        "Georgy",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        23,
                        1123,
                        true)
                )
            )
        );
    }
    @ParameterizedTest
    @NullSource
    void testHeaviestAnimalOfEachType_shouldThrowNullPointerException(List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task6.heaviestAnimalOfEachType(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testHeaviestAnimalOfEachType_shouldReturnEmptyMap(List<Animal> listAnimals) {
        assertEquals(Task6.heaviestAnimalOfEachType(listAnimals), Map.of());
    }

    @ParameterizedTest
    @MethodSource
    void testHeaviestAnimalOfEachType_shouldReturnResult(
        List<Animal> listAnimals,
        Map<Animal.Type, Animal> expectedResult) {
        assertEquals(Task6.heaviestAnimalOfEachType(listAnimals),expectedResult);
    }
}
