package edu.project4.Transformations;

import edu.project4.Elements.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
