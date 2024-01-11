package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double k = 1 / (x * x + y * y);

        return new Point(k * x, k * y);
    }
}
