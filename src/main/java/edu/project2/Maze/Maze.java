package edu.project2.Maze;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
}
