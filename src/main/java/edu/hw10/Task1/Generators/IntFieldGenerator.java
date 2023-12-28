package edu.hw10.Task1.Generators;

import edu.hw10.Task1.CustomAnnotations.Max;
import edu.hw10.Task1.CustomAnnotations.Min;
import edu.hw10.Task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class IntFieldGenerator implements FieldGenerator<Integer> {
    @Override
    public Integer generate(Random random, Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (int) minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = (int) maxAnnotation.value();
            }
        }

        return random.nextInt(min, max + 1);
    }
}

