package edu.hw4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask19 {
    private static Stream<Arguments> testValidateListAnimals_shouldReturnResult() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal(
                        " ",
                        Animal.Type.FISH,
                        Animal.Sex.FEMALE,
                        4,
                        100,
                        3,
                        true
                    )
                ),
                Map.of(
                    " ", Set.of(
                        new AnimalValidationError(
                            " ",
                            "Name must be not blank"
                        )
                    )
                )
            ),
            Arguments.of(
                List.of(
                    new Animal(
                        "wrongName",
                        Animal.Type.FISH,
                        Animal.Sex.FEMALE,
                        4,
                        100,
                        3,
                        true
                    )
                ),
                Map.of(
                    "wrongName", Set.of(
                        new AnimalValidationError(
                            "wrongName",
                            "Name must match the pattern \"^[A-Z][a-z]+( [A-Z][a-z]+)*$\""
                        )
                    )
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Dori",
                        null,
                        Animal.Sex.FEMALE,
                        4,
                        100,
                        3,
                        true
                    )
                ),
                Map.of(
                    "Dori", Set.of(
                        new AnimalValidationError(
                            "Type",
                            "Type must be not null"
                        )
                    )
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Kitty",
                        Animal.Type.FISH,
                        null,
                        4,
                        100,
                        3,
                        true
                    )
                ),
                Map.of(
                    "Kitty", Set.of(
                        new AnimalValidationError(
                            "Sex",
                            "Sex must be not null"
                        )
                    )
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Nemo",
                        Animal.Type.FISH,
                        Animal.Sex.MALE,
                        -1,
                        100,
                        3,
                        true
                    )
                ),
                Map.of(
                    "Nemo", Set.of(
                        new AnimalValidationError(
                            "Age",
                            "Age cannot be negative"
                        )
                    )
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Nemo",
                        Animal.Type.FISH,
                        Animal.Sex.MALE,
                        3,
                        -123,
                        3,
                        true
                    )
                ),
                Map.of(
                    "Nemo", Set.of(
                        new AnimalValidationError(
                            "Height",
                            "Height cannot be negative"
                        )
                    )
                )
            ),

            Arguments.of(
                List.of(
                    new Animal(
                        "Nemo",
                        Animal.Type.FISH,
                        Animal.Sex.MALE,
                        3,
                        123,
                        -3,
                        true
                    )
                ),
                Map.of(
                    "Nemo", Set.of(
                        new AnimalValidationError(
                            "Weight",
                            "Weight cannot be negative"
                        )
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    void testValidateListAnimals_shouldReturnNullPointerException(
        List<Animal> listAnimals) {
        assertThatThrownBy(
            () -> Task19.validateListAnimals(listAnimals)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @EmptySource
    void testValidateListAnimals_shouldReturnEmptyMap(
        List<Animal> listAnimals) {
        assertEquals(Task19.validateListAnimals(listAnimals), Map.of());
    }

    @ParameterizedTest
    @MethodSource
    void testValidateListAnimals_shouldReturnResult(
        List<Animal> listAnimals,
        Map<String, Set<AnimalValidationError>> exceptedMap
    ) {
        assertEquals(Task19.validateListAnimals(listAnimals), exceptedMap);
    }

}
