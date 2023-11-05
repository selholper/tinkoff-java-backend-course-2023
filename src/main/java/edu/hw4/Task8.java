package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Task8 {
    private Task8() {
    }

    public static Optional<Animal> heaviestAnimalAmongAnimalsUnderGivenHeight(
        List<Animal> listAnimals,
        int k) {
        Objects.requireNonNull(listAnimals);

        if (k < 1) {
            throw new IllegalArgumentException("Wrong value of height");
        }

        return listAnimals
            .stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }
}
