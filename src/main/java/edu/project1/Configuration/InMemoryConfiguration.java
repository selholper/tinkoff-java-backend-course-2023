package edu.project1.Configuration;

import edu.project1.Dictionary.Dictionary;
import edu.project1.Dictionary.InMemoryDictionary;

public class InMemoryConfiguration implements Configuration {
    private static final int DEFAULT_MAX_ATTEMPTS_NUMBER = 6;
    private final int maxAttemptsNumber;
    private final Dictionary dictionary;

    public InMemoryConfiguration() {
        maxAttemptsNumber = DEFAULT_MAX_ATTEMPTS_NUMBER;
        dictionary = new InMemoryDictionary();
    }

    public InMemoryConfiguration(int maxAttemptsNumber) {
        if (maxAttemptsNumber < 1) {
            throw new IllegalArgumentException("The number of attempts must be a positive integer!");
        }

        this.maxAttemptsNumber = maxAttemptsNumber;
        dictionary = new InMemoryDictionary();
    }

    @Override
    public int getMaxAttemptsNumber() {
        return maxAttemptsNumber;
    }

    @Override
    public Dictionary getDictionary() {
        return dictionary;
    }
}
