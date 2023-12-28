package edu.hw10.Task1.Generators;

import edu.hw10.Task1.CustomAnnotations.Max;
import edu.hw10.Task1.CustomAnnotations.Min;
import edu.hw10.Task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ByteFieldGenerator implements FieldGenerator<Byte> {

    @Override
    public Byte generate(Random random, Annotation[] annotations) {
        int min = Byte.MIN_VALUE;
        int max = Byte.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (int) minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = (int) maxAnnotation.value();
            }
        }

        return (byte) random.nextInt(min, max + 1);
    }
}
