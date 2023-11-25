package edu.hw7.Task1;

public final class IncrementerThread extends Thread  {
    private final AtomicCounter counter;

    IncrementerThread(AtomicCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}
