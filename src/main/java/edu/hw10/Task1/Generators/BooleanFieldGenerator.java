package edu.hw10.Task1.Generators;

import edu.hw10.Task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanFieldGenerator implements FieldGenerator<Boolean> {
    @Override
    public Boolean generate(Random random, Annotation[] annotations) {
        return random.nextBoolean();
    }
}
