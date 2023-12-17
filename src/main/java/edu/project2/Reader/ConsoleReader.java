package edu.project2.Reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

}
