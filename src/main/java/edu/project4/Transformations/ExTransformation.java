package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class ExTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        double p0 = Math.sin(theta + r);
        double p1 = Math.cos(theta - r);

        p0 = p0 * p0 * p0;
        p1 = p1 * p1 * p1;

        return new Point(
            r * (p0 + p1), r * (p0 - p1)
        );
    }
}
