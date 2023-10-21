package edu.hw2.Task3;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        Objects.requireNonNull(manager);

        if (maxAttempts < 1) {
            throw new IllegalArgumentException("The number of attempts must be positive");
        }

        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("sudo apt update && apt upgrade -y");
    }

    @SuppressWarnings("SameParameterValue")
    private void tryExecute(String command) {
        int attemptCount = 0;

        while (attemptCount < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                LOGGER.info("Command has been executed");
                return;
            } catch (Exception exception) {
                ++attemptCount;
                if (exception instanceof ConnectionException && attemptCount == maxAttempts) {
                    throw new ConnectionException("Command hasn't been executed, "
                        + " maximum number of attempts exceeded", exception);
                }
            }
        }
    }
}
