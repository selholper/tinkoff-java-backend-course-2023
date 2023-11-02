package edu.project1.Game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public sealed interface GuessResult {

    int maxAttemptsNumber();

    int currentAttemptsNumber();

    @NotNull String message();

    @Nullable GuessingWord state();

    record Launch(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return "The Hangman game starts, guess the word!";
        }
    }

    record Defeat(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Game over!";
        }
    }

    record Win(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You win!";
        }
    }

    record InvalidGuess(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Invalid input! Please enter a lowercase letter of the English alphabet.";
        }
    }

    record FailedGuess(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format("There is no such letter in the word! %d attempts left out of %d.",
                currentAttemptsNumber() + 1, maxAttemptsNumber());
        }
    }

    record SuccessfulGuess(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You guessed the letter!";
        }
    }

    record AlreadyCheckedLetter(GuessingWord state, int currentAttemptsNumber, int maxAttemptsNumber)
        implements GuessResult {
        @Override
        public @NotNull String message() {
            return "The letter is already checked!";
        }
    }
}
