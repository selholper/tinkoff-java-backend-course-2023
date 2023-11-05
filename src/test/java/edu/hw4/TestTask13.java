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

public class TestTask13 {
    private static Stream<Arguments> testListAnimalsWhichNamesConsistsMoreThanTwoWords_shouldReturnResult() {
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
                List.of()
            ),
            Arguments.of(
                List.of(
                    new Animal(
                        "Murka Ivanova Ivanovna",
                        Animal.Type.CAT,
                        Animal.Sex.FEMALE,
                        10,
                        123,
                        343,
                        true
                    )
                ),
                List.of(
                    new Animal(
                    "Murka Ivanova Ivanovna",
                    Animal.Type.CAT,
                    Animal.Sex.FEMALE,
                    10,
                    123,
                    343,
                    true
                ))
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
                        "Bobby James Fischer",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        false
                    ),

                    new Animal(
                        "Gosha Rubchinskiy Top",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        8,
                        23,
                        1289,
                        true)
                ),
                List.of(
                    new Animal(
                        "Bobby James Fischer",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        3,
                        154,
                        10,
                        false
                    ),

                    new Animal(
                        "Gosha Rubchinskiy Top",
                        Animal.Type.SPIDER,
                        Animal.Sex.MALE,
                        8,
                        23,
                        1289,
                        true)
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testListAnimalsWhichNamesConsistsMoreThanTwoWords_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task13.listAnimalsWhichNamesConsistsMoreThanTwoWords(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testListAnimalsWhichNamesConsistsMoreThanTwoWords_shouldReturnEmptyList(
        List<Animal> listAnimals) {
        assertEquals(Task13.listAnimalsWhichNamesConsistsMoreThanTwoWords(listAnimals), List.of());
    }

    @ParameterizedTest
    @MethodSource
    void testListAnimalsWhichNamesConsistsMoreThanTwoWords_shouldReturnResult(
        List<Animal> listAnimals,
        List<Animal> exceptedListAnimals) {
        assertEquals(Task13.listAnimalsWhichNamesConsistsMoreThanTwoWords(listAnimals),
            exceptedListAnimals);
    }
}
