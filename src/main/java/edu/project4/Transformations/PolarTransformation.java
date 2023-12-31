package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(Math.atan2(y, x) / Math.PI, Math.sqrt(x * x + y * y) - 1);
    }
}
