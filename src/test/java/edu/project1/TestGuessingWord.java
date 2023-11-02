package edu.project1;

import edu.project1.Game.GuessingWord;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestGuessingWord {
    @Test
    void testAlreadyInputLetterInvalidArgument() {
        GuessingWord guessingWord = new GuessingWord("123");

        assertThatThrownBy(
            () -> guessingWord.alreadyInputLetter('1')
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testSetInputLetterInvalidArgument() {
        GuessingWord guessingWord = new GuessingWord("123");

        assertThatThrownBy(
            () -> guessingWord.setInputLetter('1')
        ).isInstanceOf(IllegalArgumentException.class);


    }

    @Test
    void testOtherMethodsOfGuessingWord() {
        GuessingWord guessingWord= new GuessingWord("123");

        assertDoesNotThrow(
            guessingWord::getWord
        );

        assertDoesNotThrow(
            guessingWord::toString
        );
    }
}
