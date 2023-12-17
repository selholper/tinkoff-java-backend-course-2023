package edu.project2;

import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import edu.project2.MazeGenerators.DepthFirstMazeGenerator;
import edu.project2.MazeSolvers.DepthFirstMazeSolver;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestDepthFirstMazeGenerator {
    private static Stream<Arguments> testDepthFirstMazeGeneration_shouldReturnMazeThatHaveSolution() {
        return Stream.of(
            Arguments.of(
                11, 11,
                new Coordinate(1, 1),
                new Coordinate(9, 9)
            ),
            Arguments.of(
                13, 13,
                new Coordinate(1, 1),
                new Coordinate(11, 11)
            )
        );
    }

    private static Stream<Arguments> testDepthFirstMazeGeneration_shouldReturnMazeThatNotHaveSolution() {
        return Stream.of(
            Arguments.of(
                new Coordinate(13, 13),
                new Coordinate(1, 2)
            ),

            Arguments.of(
                new Coordinate(13, 13),
                new Coordinate(112, 232)
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testDepthFirstMazeGeneration_shouldReturnMazeThatHaveSolution(
        int height, int width,
        Coordinate c1,
        Coordinate c2) {
        Maze testMaze = new DepthFirstMazeGenerator().generate(height, width);
        List<Coordinate> listCoordinates = new DepthFirstMazeSolver().solve(testMaze, c1, c2);
        assertThat(listCoordinates).isNotEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void testDepthFirstMazeGeneration_shouldReturnMazeThatNotHaveSolution(Coordinate c1, Coordinate c2) {
        Maze testMaze = new DepthFirstMazeGenerator().generate(13, 13);
        assertThatThrownBy(
            () -> new DepthFirstMazeSolver().solve(testMaze, c1, c2)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
