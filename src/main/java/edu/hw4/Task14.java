package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task14 {
    private Task14() {
    }

    @NotNull
    public static Boolean containsDogHigherK(List<Animal> listAnimals, int k) {
        Objects.requireNonNull(listAnimals);

        if (k < 0) {
            throw new IllegalArgumentException("Wrong value of height");
        }

        return listAnimals
            .stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }
}
