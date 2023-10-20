package edu.hw2.Task2;

public final class Square extends Rectangle {
    static final int INITIAL_SIDE = 5;

    public Square() {
        super(INITIAL_SIDE, INITIAL_SIDE);
    }

    public Square(int side) {
        super(side, side);
    }

    public Square setSide(int side) {
        if (side < 0) {
            throw new IllegalArgumentException("Сторона квадрата не может быть отрицательной");
        }

        return new Square(side);
    }
}
