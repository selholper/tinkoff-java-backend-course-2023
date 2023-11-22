package edu.hw4;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task6 {
    private Task6() {
    }

    @NotNull
    public static Map<Animal.Type, Animal> heaviestAnimalOfEachType(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);
        return listAnimals
            .stream()
            .collect(Collectors.toMap(
                Animal::type,
                x -> x,
                (animal1, animal2) -> animal1.weight() < animal2.weight() ? animal2 : animal1
            ));
    }
}
