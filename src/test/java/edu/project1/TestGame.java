package edu.project1;

import edu.project1.Configuration.Configuration;
import edu.project1.Game.GameLoop;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import edu.project1.Game.DataReader;
import edu.project1.Dictionary.Dictionary;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestGame {

    private GameLoop gameLoop;
    private Configuration configuration;
    private final Queue<DataReader> input = new ArrayDeque<>();
    private final Queue<String> output = new ArrayDeque<>();

    @BeforeAll
    public void initTestingEnvironment() {
        Dictionary dictionary = makeDictionary();

        configuration = new Configuration() {
            @Override
            public int getMaxAttemptsNumber() {
                return 3;
            }

            @Override
            public Dictionary getDictionary() {
                return dictionary;
            }
        };

        gameLoop = new GameLoop(configuration) {
            @Override
            public DataReader processInput() {
                return input.poll();
            }

            @Override
            public void println(String output) {
                TestGame.this.output.add(output);
            }

            @Override
            public void print(String output) {
                TestGame.this.output.add(output);
            }
        };
    }

    private Dictionary makeDictionary() {
        return new edu.project1.Dictionary.Dictionary() {
            @Override
            public @NotNull String getSomeWord() {
                return "char";
            }
        };
    }

    @Test
    @Timeout(1)
    public void testDefeat() {
        input.clear();
        output.clear();
        gameLoop.prepare();
        input.offer(DataReader.input("a"));
        input.offer(DataReader.closed());
        gameLoop.run();

        String[] expected =
            new String[] {"The Hangman game starts, guess the word!", "The word: ****", "> ",
                "You guessed the letter!", "The word: **a*", "> ", "Game over!", "Actual word: char"};
        assertThat(Arrays.equals(expected, output.toArray())).isTrue();
    }
}
