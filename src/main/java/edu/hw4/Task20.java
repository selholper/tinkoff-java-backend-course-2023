package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task20 {
    private Task20() {
    }

    @NotNull
    public static Map<String, String> readableValidateListAnimals(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        final AnimalValidator validator = new AnimalValidator();

        return listAnimals.stream()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toMap(Animal::name, animal -> validator
                        .validateAll(animal)
                        .stream()
                        .map(AnimalValidationError::field)
                        .collect(Collectors.joining(", "))
                    ), map -> {
                        map.values().removeIf(String::isEmpty);
                        return map;
                    }
                )
            );
    }
}
