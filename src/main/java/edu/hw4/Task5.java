package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Task5 {
    private Task5() {
    }

    public static Animal.Sex mostCommonSexOfAnimals(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        if (listAnimals.isEmpty()) {
            throw new IllegalArgumentException("List is empty, cannot define most common sex of animals");
        }

        return listAnimals
            .stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparingLong(Entry::getValue))
            .map(Entry::getKey)
            .orElse(null);
    }
}
