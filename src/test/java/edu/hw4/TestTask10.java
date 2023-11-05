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

public class TestTask10 {
    private static Stream<Arguments> testListAnimalsWithAgeNotEqualsToPawNumber_shouldReturnResult() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal(
                        "Murka",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        4,
                        123,
                        3,
                        true
                    )
                ),
                List.of()
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
                        8,
                        23,
                        1,
                        true)
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
                    ),

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
    void testListAnimalsWithAgeNotEqualsToPawNumber_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task10.listAnimalsWhichAgeNotEqualsToPawNumber(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testListAnimalsWithAgeNotEqualsToPawNumber_shouldReturnEmptyList(List<Animal> listAnimals) {
        assertEquals(Task10.listAnimalsWhichAgeNotEqualsToPawNumber(listAnimals), List.of());
    }

    @ParameterizedTest
    @MethodSource
    void testListAnimalsWithAgeNotEqualsToPawNumber_shouldReturnResult(
        List<Animal> listAnimals,
        List<Animal> exceptedListAnimals) {
        assertEquals(Task10.listAnimalsWhichAgeNotEqualsToPawNumber(listAnimals), exceptedListAnimals);
    }
}
