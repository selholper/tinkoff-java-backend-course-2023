package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class DiscTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double arg = Math.PI * Math.sqrt(x * x + y * y);
        double k = (1 / Math.PI) * Math.atan2(y, x);

        return new Point(k * Math.sin(arg), k * Math.cos(arg));
    }
}
