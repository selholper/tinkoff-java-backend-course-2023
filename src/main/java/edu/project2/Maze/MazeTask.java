package edu.project2.Maze;

import edu.project2.MazeGenerators.DepthFirstMazeGenerator;
import edu.project2.MazeGenerators.MazeGenerator;
import edu.project2.MazeGenerators.PrimsMazeGenerator;
import edu.project2.MazeRenderers.ConsoleRenderer;
import edu.project2.MazeRenderers.Renderer;
import edu.project2.MazeSolvers.BreadthFirstMazeSolver;
import edu.project2.MazeSolvers.DepthFirstMazeSolver;
import edu.project2.MazeSolvers.MazeSolver;
import edu.project2.Reader.Reader;
import edu.project2.Writer.Writer;
import java.util.List;

public class MazeTask {
    private final Writer writer;
    private final Reader reader;
    private int height;
    private int width;

    public MazeTask(Writer writer, Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public void run() {
        writer.out(Messages.HELLO_MESSAGE);
        Maze maze = initMaze();
        MazeSolver solver = chooseSolver();
        Renderer renderer = new ConsoleRenderer();
        writer.out(renderer.render(maze));
        Coordinate[] coordinates = inputCoordinates();
        List<Coordinate> path = solver.solve(maze, coordinates[0], coordinates[1]);
        writer.out(renderer.render(maze, path));
        writer.out(Messages.END_MESSAGE);
    }

    private Maze initMaze() {
        inputMazeSize();
        return chooseGenerator().generate(height, width);
    }

    private void inputMazeSize() {
        writer.out(Messages.INPUT_MAZE_SIZE_MESSAGE);
        height = reader.readInt();
        width = reader.readInt();
        if (!isSizeValid(height, width)) {
            throw new IllegalArgumentException("Wrong size input");
        }
    }

    private MazeGenerator chooseGenerator() {
        writer.out(Messages.CHOOSE_GENERATOR_MESSAGE);
        int number = reader.readInt();
        return switch (number) {
            case 1 -> new DepthFirstMazeGenerator();
            case 2 -> new PrimsMazeGenerator();
            default -> throw new IllegalArgumentException("Wrong generator input");
        };
    }

    private MazeSolver chooseSolver() {
        writer.out(Messages.CHOOSE_SOLVER_MESSAGE);
        int number = reader.readInt();
        return switch (number) {
            case 1 -> new BreadthFirstMazeSolver();
            case 2 -> new DepthFirstMazeSolver();
            default -> throw new IllegalArgumentException("Wrong solver input");
        };
    }

    private Coordinate[] inputCoordinates() {
        writer.out(Messages.INPUT_COORDINATES_MESSAGE);
        int rowStart = reader.readInt();
        int columnStart = reader.readInt();
        int rowEnd = reader.readInt();
        int columnEnd = reader.readInt();
        return new Coordinate[] {
            new Coordinate(rowStart, columnStart), new Coordinate(rowEnd, columnEnd)
        };

    }

    private boolean isSizeValid(int height, int width) {
        final int maxSize = 25;
        final int minSize = 11;
        return height <= maxSize
            && width <= maxSize
            && width >= minSize
            && height >= minSize
            && height % 2 == 1
            && width % 2 == 1;
    }
}
