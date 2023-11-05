package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task7 {
    private Task7() {
    }

    @NotNull
    public static Animal kthMostOldAnimal(List<Animal> listAnimals, int k) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        if (k < 1 || k > listAnimals.size()) {
            throw new IllegalArgumentException("Wrong number of kth most old animal");
        }

        return listAnimals
            .stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .toList()
            .get(k - 1);
    }
}
