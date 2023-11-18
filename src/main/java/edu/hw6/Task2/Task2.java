package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Task2 {
    private static final String COPY = " - копия";

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        String fileName = path.getFileName().toString();
        String fileExtension = fileName.substring(fileName.indexOf('.'));
        fileName = fileName.substring(0, fileName.indexOf('.'));
        int numberOfCopy = 1;
        boolean isFileExists = true;

        while (isFileExists) {
            String newFileName = path.getParent().toString() + "/" + fileName + COPY
                + (numberOfCopy == 1 ? "" : " (" + numberOfCopy + ")") + fileExtension;
            Path newPath = Path.of(newFileName);

            if (Files.exists(newPath)) {
                ++numberOfCopy;
                continue;
            }

            Files.copy(path, newPath);
            isFileExists = false;
        }
    }
}
