package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class SpiralTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double k = 1 / r;
        double theta = Math.atan2(y, x);

        return new Point(
            k * (Math.cos(theta) + Math.sin(r)),
            k * (Math.sin(theta) - Math.cos(r))
        );
    }
}
