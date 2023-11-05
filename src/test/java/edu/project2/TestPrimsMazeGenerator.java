package edu.project2;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import edu.project2.MazeGenerators.PrimsMazeGenerator;
import edu.project2.MazeSolvers.DepthFirstMazeSolver;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestPrimsMazeGenerator {
    @Test
    public void generate_shouldReturnMazeWhichHaveSolution() {
        Maze testMaze = new PrimsMazeGenerator().generate(13, 13);
        List<Coordinate> actual =
            new DepthFirstMazeSolver().solve(testMaze, new Coordinate(1, 1), findPassageCell(testMaze));
        assertThat(actual).isNotEmpty();
    }

    private Coordinate findPassageCell(Maze maze) {
        for (int i = maze.getHeight() - 1; i > -1; --i) {
            for (int j = maze.getWidth() - 1; j > -1; --j) {
                if (maze.getGrid()[i][j].getType() == Cell.Type.PASSAGE) {
                    return new Coordinate(i, j);
                }
            }
        }

        return null;
    }
}
