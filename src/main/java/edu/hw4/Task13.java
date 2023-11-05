package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task13 {
    private Task13() {
    }

    @NotNull
    public static List<Animal> listAnimalsWhichNamesConsistsMoreThanTwoWords(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        return listAnimals
            .stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }
}
