package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class TestTask3 {
    @Test
    public void givenAList_whenConvertAfterJava8_thenReturnMapWithTheSameElements() {
        List<Animal> list = new ArrayList<>();

        list.add(
            new Animal(
                "BORIS",
                Animal.Type.CAT,
                Animal.Sex.MALE,
                10,
                20,
                30,
                false
            )
        );

        list.add(
            new Animal(
                "BOBBY",
                Animal.Type.DOG,
                Animal.Sex.MALE,
                10,
                20,
                30,
                true
            )
        );

        list.add(
            new Animal(
                "ARKADY",
                Animal.Type.FISH,
                Animal.Sex.MALE,
                10,
                20,
                30,
                false
            )
        );

        list.add(
            new Animal(
                "ARKADY",
                Animal.Type.FISH,
                Animal.Sex.MALE,
                10,
                20,
                30,
                false
            )
        );

        System.out.println(Task3.howManyAnimalsOfEachType(list));
    }
}
