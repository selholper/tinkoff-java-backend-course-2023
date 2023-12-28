package edu.project2.MazeSolvers;

import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import java.util.Collections;
import java.util.List;

public class DepthFirstMazeSolver extends AbstractMazeSolver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isNotCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        initSolver(maze);
        if (!dfs(start, end)) {
            return Collections.emptyList();
        }
        return path;
    }

    private boolean dfs(Coordinate current, Coordinate end) {
        visited[current.row()][current.column()] = true;
        path.add(current);
        if (current.equals(end)) {
            return true;
        }
        List<Coordinate> neighbours = getNeighbours(current);
        for (Coordinate neighbour : neighbours) {
            if (!visited[neighbour.row()][neighbour.column()]) {
                if (dfs(neighbour, end)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}
