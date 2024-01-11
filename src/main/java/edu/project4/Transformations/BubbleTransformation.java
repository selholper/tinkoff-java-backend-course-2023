package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class BubbleTransformation implements Transformation {
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double k = 4 / (x * x + y * y + 4);

        return new Point(k * x, k * y);
    }
}
