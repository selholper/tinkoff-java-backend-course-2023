package edu.project2.MazeRenderers;

import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
