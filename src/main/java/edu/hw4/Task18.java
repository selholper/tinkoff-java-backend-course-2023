package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class Task18 {
    private Task18() {
    }

    public static Animal heaviestFishAmongAllLists(List<List<Animal>> listAnimals) {
        Objects.requireNonNull(listAnimals);
        for (var list : listAnimals) {
            Objects.requireNonNull(list);
        }

        return listAnimals
            .stream()
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }
}
