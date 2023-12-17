package edu.hw10.Task1.Generators;

import edu.hw10.Task1.CustomAnnotations.Max;
import edu.hw10.Task1.CustomAnnotations.Min;
import edu.hw10.Task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class LongFieldGenerator implements FieldGenerator<Long> {
    @Override
    public Long generate(Random random, Annotation[] annotations) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }

        return random.nextLong(min, max + 1);
    }
}
