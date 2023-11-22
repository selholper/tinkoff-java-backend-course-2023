package edu.hw4;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task12 {
    private Task12() {
    }

    @NotNull
    public static Integer countAnimalsWhichWeightExceedsHeight(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        return listAnimals
            .stream()
            .filter(animal -> animal.weight() > animal.height())
            .toList()
            .size();
    }
}
