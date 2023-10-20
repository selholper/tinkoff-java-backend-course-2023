package edu.hw2.Task2;

public sealed class Rectangle permits Square {
    static final int INITIAL_HEIGHT = 3;
    static final int INITIAL_WIDTH = 4;
    final private int width;
    final private int height;

    public Rectangle() {
        width = INITIAL_WIDTH;
        height = INITIAL_HEIGHT;
    }

    public Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Ширина или высота прямоугольника не могут быть отрицательными");
        }

        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Ширина прямоугольника не может быть отрицательной");
        }

        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Высота прямоугольника не может быть отрицательной");
        }

        return new Rectangle(width, height);
    }

    public double area() {
        return width * height;
    }
}
