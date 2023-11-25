package edu.hw7.Task1;

public class Incrementer extends Thread  {
    private final AtomicCounter counter;

    Incrementer(AtomicCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}
