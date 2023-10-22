package edu.project1.Game;

import java.util.Arrays;

public class GuessingWord {
    private static final int ALPHABET_SIZE = 26;

    private static final char UNKNOWN_LETTER = '*';
    private final boolean[] isAlreadyGuess = new boolean[ALPHABET_SIZE];

    private final String word;
    private char[] userWord;

    public GuessingWord(String word) {
        Arrays.fill(isAlreadyGuess, false);
        this.word = word;
        initializeUserWord();
    }

    public String getWord() {
        return word;
    }

    public boolean alreadyInputLetter(char guess) {
        int letterIndex = guess - 'a';
        if (letterIndex < 0 || letterIndex >= ALPHABET_SIZE) {
            throw new IllegalArgumentException("Try to check wrong character");
        }
        return isAlreadyGuess[letterIndex];
    }

    public void setInputLetter(char guess) {
        int letterIndex = guess - 'a';
        if (letterIndex < 0 || letterIndex >= ALPHABET_SIZE) {
            throw new IllegalArgumentException("Try to set wrong character");
        }
        isAlreadyGuess[letterIndex] = true;
    }

    private void initializeUserWord() {
        userWord = new char[word.length()];
        Arrays.fill(userWord, UNKNOWN_LETTER);
    }

    public boolean containsWord(char guess) {
        return isContains(guess, word.toCharArray());
    }

    public boolean notContainsUserWord(char guess) {
        return !isContains(guess, userWord);
    }

    private boolean isContains(char finding, char[] array) {
        for (char c : array) {
            if (c == finding) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserWordOpened() {
        return notContainsUserWord(UNKNOWN_LETTER);
    }

    public void openLetter(char guess) {
        char[] actualCharArray = word.toCharArray();
        for (int i = 0; i < actualCharArray.length; i++) {
            if (actualCharArray[i] == guess) {
                userWord[i] = guess;
            }
        }
    }

    @Override
    public String toString() {
        return String.copyValueOf(userWord);
    }
}
