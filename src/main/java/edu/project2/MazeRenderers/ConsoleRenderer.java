package edu.project2.MazeRenderers;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    private static final char PASSAGE_SYMBOL = '⬛';
    private static final char WALL_SYMBOL = '⬜';
    private static final String PATH_SYMBOL = "\uD83D\uDFE1";

    @Override
    public String render(Maze maze) {
        StringBuilder renderedMaze = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = maze.getGrid()[i][j];
                if (currentCell.getType() == Cell.Type.PASSAGE) {
                    renderedMaze.append(PASSAGE_SYMBOL);
                } else {
                    renderedMaze.append(WALL_SYMBOL);
                }
            }
            renderedMaze.append('\n');
        }
        return renderedMaze.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder renderedMaze = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(new Coordinate(i, j))) {
                    renderedMaze.append(PATH_SYMBOL);
                    continue;
                }
                Cell currentCell = maze.getGrid()[i][j];
                if (currentCell.getType() == Cell.Type.PASSAGE) {
                    renderedMaze.append(PASSAGE_SYMBOL);
                } else {
                    renderedMaze.append(WALL_SYMBOL);
                }
            }
            renderedMaze.append('\n');
        }
        return renderedMaze.toString();
    }
}
