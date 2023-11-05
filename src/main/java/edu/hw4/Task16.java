package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task16 {
    private Task16() {
    }

    @NotNull
    public static List<Animal> listAnimalsSortedByTypeSexName(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        return listAnimals
            .stream()
            .sorted(
                Comparator
                    .comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name))
            .toList();
    }
}
