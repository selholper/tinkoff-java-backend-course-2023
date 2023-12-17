package edu.hw10.Task1.Generators;

import edu.hw10.Task1.CustomAnnotations.Max;
import edu.hw10.Task1.CustomAnnotations.Min;
import edu.hw10.Task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class FloatFieldGenerator implements FieldGenerator<Float> {
    @Override
    public Float generate(Random random, Annotation[] annotations) {
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }

        return random.nextFloat(min, max + 1);
    }
}

