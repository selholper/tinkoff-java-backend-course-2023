package edu.project1.Game;

import org.jetbrains.annotations.NotNull;

public class GameSession {
    private final GuessingWord guessingWord;
    private int currentAttemptsNumber;
    private final int maxAttemptsNumber;

    public GameSession(String answerWord, int maxAttemptsNumber) {
        guessingWord = new GuessingWord(answerWord);
        currentAttemptsNumber = 0;
        this.maxAttemptsNumber = maxAttemptsNumber;
    }

    public @NotNull GuessResult launch() {
        return launchGuess();
    }

    public @NotNull GuessResult guess(String guess) {
        if (!isCorrectGuess(guess)) {
            return new GuessResult.InvalidGuess(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
        }
        char guessLetter = guess.charAt(0);
        GuessResult result = processCorrectLetter(guessLetter);

        if (guessingWord.isUserWordOpened()) {
            result = winGuess();
        } else {
            if (currentAttemptsNumber >= maxAttemptsNumber) {
                result = defeatGuess();
            }
        }
        return result;
    }

    private GuessResult processCorrectLetter(char guess) {
        GuessResult result;

        if (guessingWord.containsWord(guess)) {
            if (guessingWord.notContainsUserWord(guess)) {
                guessingWord.openLetter(guess);
                result = successfulGuess();
            } else {
                result = alreadyCheckedGuess();
            }
        } else {
            if (guessingWord.alreadyInputLetter(guess)) {
                result = alreadyCheckedGuess();
            } else {
                result = failGuess();
                ++currentAttemptsNumber;
            }
        }
        guessingWord.setInputLetter(guess);
        return result;
    }

    private boolean isCorrectGuess(String guess) {
        if (guess.length() != 1) {
            return false;
        }
        return guess.charAt(0) >= 'a' && guess.charAt(0) <= 'z';
    }

    @NotNull public GuessResult giveUp() {
        return defeatGuess();
    }

    private GuessResult launchGuess() {
        return new GuessResult.Launch(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
    }

    private GuessResult successfulGuess() {
        return new GuessResult.SuccessfulGuess(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
    }

    private GuessResult winGuess() {
        return new GuessResult.Win(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
    }

    private GuessResult failGuess() {
        return new GuessResult.FailedGuess(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
    }

    private GuessResult alreadyCheckedGuess() {
        return new GuessResult.AlreadyCheckedLetter(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
    }

    private GuessResult defeatGuess() {
        return new GuessResult.Defeat(guessingWord, currentAttemptsNumber, maxAttemptsNumber);
    }
}
