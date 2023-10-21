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
            throw new IllegalArgumentException("The side of a square cannot be negative");
        }

        return new Square(side);
    }
}
