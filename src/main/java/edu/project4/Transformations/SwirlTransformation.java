package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double rSqr = x * x + y * y;

        return new Point(
            x * Math.sin(rSqr) - y * Math.cos(rSqr),
            x * Math.cos(rSqr) + y * Math.sin(rSqr)
        );
    }
}
