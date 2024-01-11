package edu.project4.Transformations;

import edu.project4.Elements.Point;
import java.util.concurrent.ThreadLocalRandom;

public class JuliaTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(Math.sqrt(x * x + y * y));
        double theta = Math.atan2(y, x);
        double omega = (ThreadLocalRandom.current().nextBoolean() ? 1 : 0) * Math.PI;

        return new Point(
            r * Math.cos(theta / 2 + omega),
            r * Math.sin(theta / 2 + omega)
        );
    }
}
