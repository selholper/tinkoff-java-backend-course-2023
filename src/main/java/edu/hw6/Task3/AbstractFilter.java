package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    @Override
    boolean accept(Path path) throws IOException;

    default AbstractFilter and(AbstractFilter filter) {
        return path -> accept(path) && filter.accept(path);
    }
}
