package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class CosineTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(
            Math.cos(Math.PI * x) * Math.cosh(y),
            -Math.sin(Math.PI * x) * Math.sinh(y)
        );
    }
}
