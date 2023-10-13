package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CHESS_BOARD_SIZE = 8;
    private static final int KNIGHT_STEP_ONE = 1;
    private static final int KNIGHT_STEP_TWO = 2;

    private Task8() {
    }

    private static boolean isKnightCapture(int[][] chessBoard, int x, int y) {
        if (chessBoard[x][y] == 0) {
            return false;
        }

        int[] dx = {
            -KNIGHT_STEP_TWO, -KNIGHT_STEP_TWO,
            -KNIGHT_STEP_ONE, -KNIGHT_STEP_ONE,
            KNIGHT_STEP_ONE, KNIGHT_STEP_ONE,
            KNIGHT_STEP_TWO, KNIGHT_STEP_TWO
        };
        int[] dy = {
            -KNIGHT_STEP_ONE, KNIGHT_STEP_ONE,
            -KNIGHT_STEP_TWO, KNIGHT_STEP_TWO,
            -KNIGHT_STEP_TWO, KNIGHT_STEP_TWO,
            -KNIGHT_STEP_ONE, KNIGHT_STEP_ONE
        };

        for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
            int capturePositionX = x + dx[i];
            int capturePositionY = y + dy[i];
            if (capturePositionX >= 0 && capturePositionX < CHESS_BOARD_SIZE
                && capturePositionY >= 0 && capturePositionY < CHESS_BOARD_SIZE
                && chessBoard[capturePositionX][capturePositionY] == 1) {
                return true;
            }
        }

        return false;
    }

    public static boolean knightBoardCapture(int[][] chessBoard) {
        Objects.requireNonNull(chessBoard);

        if (chessBoard.length != CHESS_BOARD_SIZE) {
            throw new IllegalArgumentException("The number of rows on the chessboard "
                + "should be equal to 8");
        }

        for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
            Objects.requireNonNull(chessBoard[i]);
        }

        for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
            if (chessBoard[i].length != CHESS_BOARD_SIZE) {
                throw new IllegalArgumentException("The size of each column on the chessboard"
                    + " should be equal to 8");
            }

            for (int j = 0; j < CHESS_BOARD_SIZE; ++j) {
                if (chessBoard[i][j] != 0 && chessBoard[i][j] != 1) {
                    throw new IllegalArgumentException("the value of the chessboard cell "
                        + "should be zero or one");
                }
            }
        }

        LOGGER.trace("Check whether one knight can capture another on the following chessboard");
        for (int[] row : chessBoard) {
            LOGGER.trace("{}", row);
        }

        for (int i = 0; i < CHESS_BOARD_SIZE; ++i) {
            for (int j = 0; j < CHESS_BOARD_SIZE; ++j) {
                if (isKnightCapture(chessBoard, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
