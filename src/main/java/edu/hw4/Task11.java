package edu.hw4;


import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task11 {
    final static int MIN_HEIGHT = 100;

    private Task11() {
    }

    @NotNull
    public static List<Animal> listAnimalsGreaterOneMetreWhichBites(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        return listAnimals
            .stream()
            .filter(animal -> animal.bites() && animal.height() > MIN_HEIGHT)
            .toList();
    }
}
