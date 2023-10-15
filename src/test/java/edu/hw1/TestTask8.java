package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask8 {
    @Test
    @DisplayName("Тестирование функции проверки расстановки коней на шахматном поле")
    void testCorrectKnightBoardCapture() {
        // given
        int[][] chessBoard = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        // when
        boolean isCapture = Task8.knightBoardCapture(chessBoard);
        // then
        assertThat(isCapture).isTrue();

        // given
        chessBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };
        // when
        isCapture = Task8.knightBoardCapture(chessBoard);
        // then
        assertThat(isCapture).isFalse();

        // given
        chessBoard = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        // when
        isCapture = Task8.knightBoardCapture(chessBoard);
        // then
        assertThat(isCapture).isFalse();

        // given
        chessBoard = new int[][] {
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0}
        };
        // when
        isCapture = Task8.knightBoardCapture(chessBoard);
        // then
        assertThat(isCapture).isTrue();

        // given
        chessBoard = new int[][] {
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0}
        };
        // when
        isCapture = Task8.knightBoardCapture(chessBoard);
        // then
        assertThat(isCapture).isFalse();
    }

    @Test
    @DisplayName("Проверка исключений для функции проверки расстановки коней на шахматном поле")
    void testIncorrectKnightBoardCapture() {
        assertThatThrownBy(
            () -> Task8.knightBoardCapture(new int[][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            })
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task8.knightBoardCapture(new int[][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            })
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task8.knightBoardCapture(null)
        ).isInstanceOf(java.lang.NullPointerException.class);

        assertThatThrownBy(
            () -> Task8.knightBoardCapture(new int[][] {
                null,
                null,
                {},
                {},
                {},
                {},
                {},
                {}
            })
        ).isInstanceOf(java.lang.NullPointerException.class);

        assertThatThrownBy(
            () -> Task8.knightBoardCapture(new int[][] {
                {16243, 1, 0, 1, -1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, -1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0}
            })
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
