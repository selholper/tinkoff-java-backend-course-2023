package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task15 {
    private Task15() {
    }

    @NotNull
    public static Map<Animal.Type, Integer> mapAnimalsEachTypeTotalWeightFromKToLAge(
        List<Animal> listAnimals,
        int k,
        int l
    ) {
        Objects.requireNonNull(listAnimals);

        if (k < 0 || l < k) {
            throw new IllegalArgumentException("Wrong age arguments");
        }

        return listAnimals
            .stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }
}
