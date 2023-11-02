package edu.project2.Writer;

public class ConsoleWriter implements Writer {
    @SuppressWarnings("checkstyle:RegexpSinglelineJava") @Override
    public void out(String string) {
        System.out.println(string);
    }
}
