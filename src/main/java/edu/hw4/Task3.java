package edu.hw4;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task3 {
    private Task3() {
    }

    @NotNull
    public static Map<Animal.Type, Integer> howManyAnimalsOfEachType(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        return listAnimals
            .stream()
            .collect(Collectors.groupingByConcurrent(
                Animal::type,
                Collectors.summingInt(x -> 1)
            ));
    }
}
