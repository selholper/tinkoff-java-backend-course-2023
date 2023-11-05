package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task9 {
    private Task9() {
    }

    @NotNull
    public static Integer animalsPawSum(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        return listAnimals
            .stream()
            .mapToInt(Animal::paws)
            .sum();
    }
}
