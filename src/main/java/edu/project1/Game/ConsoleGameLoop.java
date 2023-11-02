package edu.project1.Game;

import edu.project1.Configuration.Configuration;
import java.util.Scanner;

public class ConsoleGameLoop extends GameLoop {

    private final Scanner input;

    public ConsoleGameLoop(Configuration configuration) {
        super(configuration);
        input = new Scanner(System.in);
    }

    @Override
    public DataReader processInput() {
        if (!input.hasNext()) {
            return DataReader.closed();
        }
        return DataReader.input(input.nextLine());
    }

    @Override
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void print(String output) {
        System.out.print(output);
    }
}
