package edu.hw9.Task3;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import edu.project2.Maze.MazeUtils;
import edu.project2.MazeSolvers.MazeSolver;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestParallelDepthFirstMazeSolver {
    private final MazeSolver solver = new ParallelDepthFirstMazeSolver();
    private static Maze maze;

    @BeforeAll
    static void initMaze() {
        Cell[][] grid = MazeUtils.getOnlyPassageCells(7, 7);
        MazeUtils.createOffset(grid);
        for (int i = 2; i < 7; i += 2) {
            for (int j = 2; j < 6; ++j) {
                grid[i][j].setType(Cell.Type.WALL);
            }
        }
        maze = new Maze(7, 7, grid);
    }

    @Test
    public void testSolveMethod_shouldFindCorrectPath() {
        List<Coordinate> listCoordinates = solver.solve(
            maze,
            new Coordinate(1, 1),
            new Coordinate(5, 5)
        );
        assertThat(listCoordinates).containsExactlyInAnyOrder(
            new Coordinate(5, 4),
            new Coordinate(5, 3),
            new Coordinate(5, 2),
            new Coordinate(5, 1),
            new Coordinate(4, 1),
            new Coordinate(3, 1),
            new Coordinate(2, 1),
            new Coordinate(1, 1),
            new Coordinate(5, 5)
        );
    }

    @Test
    public void testSolveMethod_shouldThrowExceptionForIncorrectCoordinates() {
        assertThatThrownBy(
            () -> solver.solve(
                maze,
                new Coordinate(-1, 0),
                new Coordinate(9, 9)
            )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
