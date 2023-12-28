package edu.hw10.Task1.Generators;

import edu.hw10.Task1.CustomAnnotations.Max;
import edu.hw10.Task1.CustomAnnotations.Min;
import edu.hw10.Task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class DoubleFieldGenerator implements FieldGenerator<Double> {
    @Override
    public Double generate(Random random, Annotation[] annotations) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }

        return random.nextDouble(min, max + 1);
    }
}

