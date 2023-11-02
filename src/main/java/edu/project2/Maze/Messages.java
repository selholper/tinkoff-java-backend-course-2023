package edu.project2.Maze;

public final class Messages {
    private Messages() {
    }

    public static final String HELLO_MESSAGE =
            """
            Maze Solver;
            """;
    public static final String INPUT_MAZE_SIZE_MESSAGE =
            """
            Enter the height and the width of the maze (each value must be odd)
            Minimum value - 11, maximum value - 25.
            Example: 11 11""";
    public static final String CHOOSE_GENERATOR_MESSAGE =
            """
            Choose the way of maze generation:
            1 - depth first maze generator
            2 - Prim's maze generator""";
    public static final String CHOOSE_SOLVER_MESSAGE =
            """
            Choose the maze solver algorithm:
            1 - breadth first maze solver
            2 - depth first maze solver""";
    public static final String INPUT_COORDINATES_MESSAGE =
            """
            This is your maze!
            Enter the coordinates of the start cell and the finish cell, between which you need to find a path.
            Example: 1 1 3 4""";
    public static final String END_MESSAGE = "Solution is find!";
}
