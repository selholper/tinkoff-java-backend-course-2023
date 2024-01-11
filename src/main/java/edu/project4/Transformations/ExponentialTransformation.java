package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double k = Math.exp(x - 1);

        return new Point(
            k * Math.cos(Math.PI * y),
            k * Math.sin(Math.PI * y)
        );
    }
}
