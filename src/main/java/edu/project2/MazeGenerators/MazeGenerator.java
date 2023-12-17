package edu.project2.MazeGenerators;

import edu.project2.Maze.Maze;

public interface MazeGenerator {
    Maze generate(int height, int width);
}
