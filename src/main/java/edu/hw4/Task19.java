package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task19 {
    private Task19() {
    }

    @NotNull
    public static Map<String, Set<AnimalValidationError>> validateListAnimals(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        final AnimalValidator animalValidator = new AnimalValidator();

        return listAnimals.stream()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toMap(
                        Animal::name, animalValidator::validateAll
                    ), map -> {
                        map.values().removeIf(Set::isEmpty);
                        return map;
                    }
                )
            );
    }
}
