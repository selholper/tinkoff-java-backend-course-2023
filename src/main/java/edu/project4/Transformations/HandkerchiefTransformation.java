package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        return new Point(r * Math.sin(theta + r),  r * Math.sin(theta - r));
    }
}
