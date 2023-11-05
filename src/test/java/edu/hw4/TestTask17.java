package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask17 {
    private static Stream<Arguments> testisSpidersBiteMoreOftenThenDogs_shouldReturnFalse() {
        return Stream.of(
            Arguments.of(List.of(
                new Animal(
                    "Murka",
                    Animal.Type.CAT,
                    Animal.Sex.FEMALE,
                    10,
                    123,
                    3,
                    true)
            )),
            Arguments.of(List.of(
                new Animal(
                    "Gosha",
                    Animal.Type.SPIDER,
                    Animal.Sex.MALE,
                    3,
                    23,
                    1,
                    false
                ),
                new Animal(
                    "Murka",
                    Animal.Type.CAT,
                    Animal.Sex.FEMALE,
                    10,
                    123,
                    3,
                    true)
            )),
            Arguments.of(List.of(
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
                    "Murka",
                    Animal.Type.CAT,
                    Animal.Sex.FEMALE,
                    10,
                    123,
                    3,
                    true)
            )),
            Arguments.of(List.of(
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
            ))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testisSpidersBiteMoreOftenThenDogs_shouldReturnFalse(List<Animal> listAnimals) {
        assertThat(Task17.isSpidersBiteMoreOftenThenDogs(listAnimals)).isFalse();
    }
}
