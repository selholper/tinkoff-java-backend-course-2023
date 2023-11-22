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

public class TestTask14 {
    private static Stream<Arguments> testContainsDogHigherK_shouldReturnResult() {
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
                100,
                false
            ),
            Arguments.of(
                List.of(
                    new Animal(
                        "Booby",
                        Animal.Type.DOG,
                        Animal.Sex.MALE,
                        10,
                        123,
                        343,
                        true
                    )
                ),
                100,
                true
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
                1000,
                false
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testContainsDogHigherK_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task14.containsDogHigherK(listAnimals, 10)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testContainsDogHigherK_shouldThrowIllegalArgumentException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task14.containsDogHigherK(listAnimals, -1)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    void testContainsDogHigherK_shouldReturnResult(
        List<Animal> listAnimals,
        int k,
        boolean contains) {
        assertEquals(Task14.containsDogHigherK(listAnimals, k), contains);
    }
}
