package edu.project2.Maze;

import edu.project2.Maze.Cell.Type;

public final class MazeUtils {

    private MazeUtils() {
    }

    public static Cell[][] getOnlyWallCells(int height, int width) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j, Type.WALL);
            }
        }
        return cells;
    }

    public static boolean isCoordinatesValid(int row, int column, int height, int width) {
        return row > 0 && row < height - 1 && column > 0 && column < width - 1;
    }
}
