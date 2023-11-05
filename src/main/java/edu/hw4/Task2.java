package edu.hw4;


import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private Task2() {
    }

    @NotNull
    public static List<Animal> sortByWeight(List<Animal> listAnimals, int k) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        if (k < 0 || k > listAnimals.size()) {
            throw new IllegalArgumentException("Wrong number of elements for sorting");
        }

        return listAnimals
            .stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }
}
