package edu.hw2.Task3;

import java.util.Random;

public class FaultyConnectionManager implements ConnectionManager {
    private final Random random;

    public FaultyConnectionManager() {
        random = new Random();
    }

    public FaultyConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(random);
    }
}
