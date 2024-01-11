package edu.project4.Elements;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int COLOR_UPPER_BOUND = 256;

    private static boolean isAffineCoefficientsValid(double a, double b, double d, double e) {
        return (a * a + d * d < 1) && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    public static AffineCoefficients create() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;

        do {
            a = ThreadLocalRandom.current().nextDouble(-1, 1);
            b = ThreadLocalRandom.current().nextDouble(-1, 1);
            c = ThreadLocalRandom.current().nextDouble(-1, 1);
            d = ThreadLocalRandom.current().nextDouble(-1, 1);
            e = ThreadLocalRandom.current().nextDouble(-1, 1);
            f = ThreadLocalRandom.current().nextDouble(-1, 1);
        } while (!isAffineCoefficientsValid(a, b, d, e));

        return new AffineCoefficients(a, b, c, d, e, f, getRandomColor());
    }

    private static Color getRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, COLOR_UPPER_BOUND),
            ThreadLocalRandom.current().nextInt(0, COLOR_UPPER_BOUND),
            ThreadLocalRandom.current().nextInt(0, COLOR_UPPER_BOUND)
        );
    }
}
