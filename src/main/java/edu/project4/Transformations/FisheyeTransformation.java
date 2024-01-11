package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class FisheyeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double k = 2 / (r + 1);

        return new Point(k * y, k * x);
    }
}
