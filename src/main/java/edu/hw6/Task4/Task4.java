package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class Task4 {
    private Task4() {
    }

    public static void outputStreamComposition(Path filePath) {
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);

            printWriter.write("Programming is learned by writing programs. - Brian Kernighan");

            printWriter.flush();
            checkedOutputStream.close();
            bufferedOutputStream.close();
            outputStreamWriter.close();
            printWriter.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
