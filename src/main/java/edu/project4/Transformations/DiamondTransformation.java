package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class DiamondTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double theta = Math.atan2(x, y);
        double r = Math.sqrt(x * x + y * y);

        return new Point(
            Math.sin(theta) * Math.cos(r),
            Math.cos(theta) * Math.sin(r)
        );
    }
}
