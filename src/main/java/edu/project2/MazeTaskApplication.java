package edu.project2;

import edu.project2.Maze.MazeTask;
import edu.project2.Reader.ConsoleReader;
import edu.project2.Writer.ConsoleWriter;

public final class MazeTaskApplication {
    private MazeTaskApplication() {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        new MazeTask(new ConsoleWriter(), new ConsoleReader()).run();
    }
}
