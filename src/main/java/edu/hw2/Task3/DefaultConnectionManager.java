package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random;

    DefaultConnectionManager() {
        random = new Random();
    }

    DefaultConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        boolean isConnectionFault = random.nextBoolean();

        if (isConnectionFault) {
            return new FaultyConnection(random);
        }

        return new StableConnection();
    }
}
