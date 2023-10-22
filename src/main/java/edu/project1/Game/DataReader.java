package edu.project1.Game;

public final class DataReader {
    private enum InputState {
        AVAILABLE, CLOSED
    }

    private final String input;
    private final InputState inputStatus;

    private DataReader(String input, InputState inputStatus) {
        this.input = input;
        this.inputStatus = inputStatus;
    }

    public static DataReader closed() {
        return new DataReader(null, InputState.CLOSED);
    }

    public static DataReader input(String input) {
        return new DataReader(input, InputState.AVAILABLE);
    }

    public String getInput() {
        return input;
    }

    public boolean isInputStopped() {
        return inputStatus == InputState.CLOSED;
    }
}
