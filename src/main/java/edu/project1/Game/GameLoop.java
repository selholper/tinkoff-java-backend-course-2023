package edu.project1.Game;

import edu.project1.Configuration.Configuration;
import edu.project1.Dictionary.Dictionary;
import java.util.Objects;

public abstract class GameLoop {

    protected GameSession gameSession;
    protected final Configuration configuration;
    protected GameState status = GameState.STOP;

    public GameLoop(Configuration configuration) {
        this.configuration = configuration;
    }

    public void prepare() {
        Objects.requireNonNull(configuration);
        Objects.requireNonNull(configuration.getDictionary());

        Dictionary dictionary = configuration.getDictionary();
        String word = dictionary.getSomeWord();

        gameSession = new GameSession(word, configuration.getMaxAttemptsNumber());
    }

    public void run() {
        status = GameState.EXECUTING;
        processGameLoop();
    }

    private void processGameLoop() {
        GuessResult result = gameSession.launch();
        printGuessResult(result);
        while (status == GameState.EXECUTING) {
            print("> ");
            DataReader dataReader = processInput();
            if (dataReader.isInputStopped()) {
                result = gameSession.giveUp();
            } else {
                result = gameSession.guess(dataReader.getInput());
            }
            printGuessResult(result);
            if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                stop();
            }
        }
    }

    private void printGuessResult(GuessResult guessResult) {
        println(guessResult.message());
        if (guessResult instanceof GuessResult.Defeat) {
            println(String.format("Actual word: %s", guessResult.state().getWord()));
        } else {
            println(String.format("The word: %s", guessResult.state()));
        }
    }

    public void stop() {
        status = GameState.STOP;
    }

    public abstract DataReader processInput();

    public abstract void println(String output);

    public abstract void print(String output);
}
