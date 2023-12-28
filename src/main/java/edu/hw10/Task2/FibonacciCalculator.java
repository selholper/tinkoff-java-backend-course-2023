package edu.hw10.Task2;

public interface FibonacciCalculator {
    @Cache(persist = true)
    long fib(long number);
}
