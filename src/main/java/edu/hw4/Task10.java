package edu.hw4;


import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task10 {
    private Task10() {
    }

    @NotNull
    public static List<Animal> listAnimalsWhichAgeNotEqualsToPawNumber(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        return listAnimals
            .stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }
}
