package edu.hw9.Task3;

import edu.project2.Maze.Coordinate;
import edu.project2.Maze.Maze;
import edu.project2.MazeSolvers.AbstractMazeSolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;

public class ParallelDepthFirstMazeSolver extends AbstractMazeSolver {
    private static final int LOCK_TIME = 60;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isNotCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        initSolver(maze);
        boolean result = forkJoinPool.invoke(new ParallelDepthFirstSearch(start, end));
        if (!result) {
            return Collections.emptyList();
        }
        path.add(end);

        return path;
    }

    private class ParallelDepthFirstSearch extends RecursiveTask<Boolean> {
        private final Coordinate current;
        private final Coordinate end;

        ParallelDepthFirstSearch(Coordinate current, Coordinate end) {
            this.current = current;
            this.end = end;
        }

        @Override
        @SneakyThrows
        protected Boolean compute() {
            List<Coordinate> coordinatesForFork = new ArrayList<>();
            coordinatesForFork.add(current);

            if (!reentrantLock.tryLock(LOCK_TIME, TimeUnit.SECONDS)) {
                return false;
            }

            try {
                visited[current.row()][current.column()] = true;
            } finally {
                reentrantLock.unlock();
            }

            if (current.equals(end)) {
                return true;
            }

            List<ParallelDepthFirstSearch> forks = new ArrayList<>();
            List<Coordinate> neighbours = getNeighbours(current);
            for (Coordinate neighbour : neighbours) {
                if (!visited[neighbour.row()][neighbour.column()]) {
                    ParallelDepthFirstSearch task = new ParallelDepthFirstSearch(neighbour, end);
                    task.fork();
                    forks.add(task);
                }
            }

            for (ParallelDepthFirstSearch task : forks) {
                if (task.join()) {
                    path.addAll(coordinatesForFork);
                    return true;
                }
            }

            return false;
        }
    }
}
