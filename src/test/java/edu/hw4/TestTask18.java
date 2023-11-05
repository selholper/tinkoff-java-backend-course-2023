package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTask18 {
    private static Stream<Arguments> testHeaviestFishAmongAllLists_shouldReturnResult() {
        return Stream.of(
            Arguments.of(
                List.of(
                    List.of(
                        new Animal(
                            "Murka",
                            Animal.Type.FISH,
                            Animal.Sex.FEMALE,
                            4,
                            100,
                            3,
                            true
                        )
                    ),
                    List.of(
                        new Animal(
                            "Bobby",
                            Animal.Type.FISH,
                            Animal.Sex.FEMALE,
                            4,
                            100,
                            30,
                            true
                        )
                    )
                ),
                new Animal(
                    "Bobby",
                    Animal.Type.FISH,
                    Animal.Sex.FEMALE,
                    4,
                    100,
                    30,
                    true
                )
            ),

            Arguments.of(
                List.of(
                    List.of(
                        new Animal(
                            "Charlie",
                            Animal.Type.FISH,
                            Animal.Sex.MALE,
                            10,
                            123,
                            3432,
                            true
                        ),

                        new Animal(
                            "Booby",
                            Animal.Type.SPIDER,
                            Animal.Sex.MALE,
                            10,
                            123,
                            343,
                            true
                        )
                    ),
                    List.of(
                        new Animal(
                            "Booby",
                            Animal.Type.FISH,
                            Animal.Sex.MALE,
                            10,
                            123,
                            343,
                            true
                        ),

                        new Animal(
                            "Charlie",
                            Animal.Type.FISH,
                            Animal.Sex.MALE,
                            10,
                            123,
                            343,
                            true
                        )
                    )
                ),

                new Animal(
                    "Charlie",
                    Animal.Type.FISH,
                    Animal.Sex.MALE,
                    10,
                    123,
                    3432,
                    true
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testHeaviestFishAmongAllLists_shouldReturnNullPointerException(
        List<List<Animal>> listAnimals) {
        assertThatThrownBy(
            () -> Task18.heaviestFishAmongAllLists(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    void testHeaviestFishAmongAllLists_shouldReturnNullForListContainsNullLists(
        List<Animal> listAnimals
    ) {
        List<List<Animal>> lists = new ArrayList<>();

        lists.add(listAnimals);
        lists.addFirst(listAnimals);

        assertThatThrownBy(
            () -> Task18.heaviestFishAmongAllLists(lists)
        ).isInstanceOf(NullPointerException.class);
    }


    @ParameterizedTest
    @EmptySource
    void testHeaviestFishAmongAllLists_shouldReturnNullForEmptyList(
        List<List<Animal>> listAnimals) {
        assertNull(Task18.heaviestFishAmongAllLists(listAnimals));
    }

    @ParameterizedTest
    @MethodSource
    void testHeaviestFishAmongAllLists_shouldReturnResult(
        List<List<Animal>> lists,
        Animal animalFish
    ) {
        assertEquals(Task18.heaviestFishAmongAllLists(lists), animalFish);
    }
}
