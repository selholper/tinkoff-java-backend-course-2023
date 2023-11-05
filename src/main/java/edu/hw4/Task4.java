package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task4 {
    private Task4() {
    }

    @NotNull
    public static Animal longestNameAnimal(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);
        if (listAnimals.contains(null)) {
            throw new NullPointerException("List contains null elements");
        }

        if (listAnimals.isEmpty()) {
            throw new IllegalArgumentException("List is empty, cannot define longest name animal");
        }

        return listAnimals
            .stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .get();
    }
}
