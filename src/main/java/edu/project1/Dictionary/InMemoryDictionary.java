package edu.project1.Dictionary;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class InMemoryDictionary implements Dictionary {
    private final Random random = new Random();
    private final String[] words = {
        "rain",
        "storm",
        "breeze",
        "thunder",
        "lightning"
    };

    @Override
    public @NotNull String getSomeWord() {
        int someIndex = random.nextInt(words.length);
        return words[someIndex];
    }
}
