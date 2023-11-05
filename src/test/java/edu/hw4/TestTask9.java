package edu.hw4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import java.util.ArrayList;
import java.util.List;

public class TestTask9 {
    @ParameterizedTest
    @EmptySource
    void testAnimalsPawSum_shouldReturnZeroForEmptyList(List<Animal> animals) {
        System.out.println(Task9.animalsPawSum(animals));
    }
}
