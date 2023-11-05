package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task1 {
    private Task1() {
    }

    @NotNull
    public static List<Animal> sortByHeight(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        return listAnimals
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }
}
