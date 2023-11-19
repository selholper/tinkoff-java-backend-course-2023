package edu.project3.output;

public class CLIWriter implements Writer {

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void out(String message) {
        System.out.println(message);
    }
}
