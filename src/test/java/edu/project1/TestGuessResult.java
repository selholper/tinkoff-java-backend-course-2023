package edu.project1;

import edu.project1.Game.GuessResult;
import edu.project1.Game.GuessingWord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestGuessResult {
    private static Stream <Arguments> guessResultArgumentsExample() {
        return Stream.of(
            Arguments.of(new GuessingWord("cloud"), 0, 5),
            Arguments.of(new GuessingWord("hurricane"), 1, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testLaunch_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.Launch(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testDefeat_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.Defeat(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testWin_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.Win(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testInvalidGuess_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.InvalidGuess(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testFailedGuess_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.FailedGuess(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testSuccessfulGuess_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.SuccessfulGuess(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void testAlreadyCheckedLetter_shouldNotThrowAnyExceptions(GuessingWord word, int attempt, int maxAttempt) {
        GuessResult guessResult = new GuessResult.AlreadyCheckedLetter(word, attempt, maxAttempt);
        assertDoesNotThrow(guessResult::message);
    }
}
