package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Random random;

    FaultyConnection() {
        random = new Random();
    }

    FaultyConnection(Random random) {
        this.random = random;
    }

    @Override
    public void execute(String command) {
        boolean isConnectionFault = random.nextBoolean();

        if (isConnectionFault) {
            throw new ConnectionException("Connection doesn't work");
        }
        LOGGER.info(command + " is executed");
    }

    @Override
    public void close() {
        LOGGER.info("Connection closed");
    }
}
