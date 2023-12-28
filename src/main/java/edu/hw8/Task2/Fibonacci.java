package edu.hw8.Task2;

public final class Fibonacci {
    private Fibonacci() {
    }

    public static int calculateFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}
