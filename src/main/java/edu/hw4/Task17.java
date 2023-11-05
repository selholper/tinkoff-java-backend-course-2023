package edu.hw4;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Task17 {
    private Task17() {
    }

    @NotNull
    public static Boolean isSpidersBiteMoreOftenThenDogs(List<Animal> listAnimals) {
        Objects.requireNonNull(listAnimals);

        return listAnimals
            .stream()
            .filter(animal ->
                animal.bites()
                && (animal.type() == Animal.Type.DOG
                || animal.type() == Animal.Type.SPIDER)
            )
            .collect(
                Collectors.collectingAndThen(
                    Collectors.groupingBy(Animal::type, Collectors.counting()),
                    map ->
                        (map.containsKey(Animal.Type.SPIDER)
                        && !map.containsKey(Animal.Type.DOG))
                        || (map.containsKey(Animal.Type.SPIDER)
                        && map.containsKey(Animal.Type.DOG)
                        && map.get(Animal.Type.SPIDER) > map.get(Animal.Type.DOG))
                )
            );
    }
}
