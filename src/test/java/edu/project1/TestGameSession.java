package edu.project1;

import edu.project1.Game.GameSession;
import edu.project1.Game.GuessResult;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestGameSession {
    @Test
    public void testGameSession_shouldReturnLaunch() {
        GameSession gameSession = new GameSession("some", 3);
        GuessResult result = gameSession.launch();
        assertThat(result).isInstanceOf(GuessResult.Launch.class);
    }

    @Test
    public void testGameSession_shouldReturnDefeat() {
        GameSession gameSession = new GameSession("some", 5);
        gameSession.guess("q");
        gameSession.guess("w");
        gameSession.guess("p");
        gameSession.guess("r");
        GuessResult result = gameSession.guess("a");
        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    public void testGameSession_shouldReturnWin() {
        GameSession gameSession = new GameSession("some", 3);
        gameSession.guess("s");
        gameSession.guess("o");
        gameSession.guess("m");
        GuessResult result = gameSession.guess("e");
        assertThat(result).isInstanceOf(GuessResult.Win.class);
    }


    @Test
    public void testGameSession_shouldReturnGuessedLetter() {
        GameSession gameSession = new GameSession("test", 10);
        gameSession.guess("t");
        GuessResult result = gameSession.guess("s");
        assertThat(result).isInstanceOf(GuessResult.SuccessfulGuess.class);
    }

    @Test
    public void testGameSession_shouldReturnNotGuessedLetter() {
        GameSession session = new GameSession("hover", 10);
        GuessResult result = session.guess("p");
        assertThat(result).isInstanceOf(GuessResult.FailedGuess.class);
    }

    @Test
    public void testGameSession_shouldReturnLetterAlreadyCheck() {
        GameSession session = new GameSession("hover", 10);
        session.guess("h");
        GuessResult result = session.guess("h");
        assertThat(result).isInstanceOf(GuessResult.AlreadyCheckedLetter.class);

        session.guess("p");
        result = session.guess("p");
        assertThat(result).isInstanceOf(GuessResult.AlreadyCheckedLetter.class);
    }


    @Test
    public void testGameSession_shouldReturnInvalidInput() {
        GameSession session = new GameSession("hover", 10);
        session.guess("p");
        GuessResult result = session.guess("lol");
        assertThat(result).isInstanceOf(GuessResult.InvalidGuess.class);
    }
}
