package edu.project1;

import edu.project1.Configuration.Configuration;
import edu.project1.Configuration.InMemoryConfiguration;
import edu.project1.Game.ConsoleGameLoop;
import edu.project1.Game.GameLoop;


public final class ConsoleHangmanGame {
    private final GameLoop gameLoop;

    public ConsoleHangmanGame() {
        Configuration configuration = new InMemoryConfiguration();
        gameLoop = new ConsoleGameLoop(configuration);
    }

    public ConsoleHangmanGame(int maxAttemptsNumber) {
        Configuration configuration = new InMemoryConfiguration(maxAttemptsNumber);
        gameLoop = new ConsoleGameLoop(configuration);
    }

    public void run() {
        gameLoop.prepare();
        gameLoop.run();
    }

    // Main method for running console game
    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        new ConsoleHangmanGame().run();
    }

}
