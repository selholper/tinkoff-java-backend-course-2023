package edu.project4.Elements;

import java.util.concurrent.ThreadLocalRandom;

public record Rectangle(double x, double y, double width, double height) {
    public boolean doesNotContainPoint(Point p) {
        return !(p.x() >= x) || !(p.x() <= x + width)
            || !(p.y() >= y) || !(p.y() <= y + height);
    }

    public Point getRandomPoint() {
        double randomPointX = ThreadLocalRandom.current().nextDouble(0, width);
        double randomPointY = ThreadLocalRandom.current().nextDouble(0, height);

        return new Point(randomPointX, randomPointY);
    }
}
