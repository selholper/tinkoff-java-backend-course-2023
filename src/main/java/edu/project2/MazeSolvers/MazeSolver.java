package edu.project2.MazeSolvers;

import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.List;

public interface MazeSolver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
