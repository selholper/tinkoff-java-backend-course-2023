package edu.project4;

import edu.project4.Elements.FractalImage;
import edu.project4.Elements.ImageFormat;
import edu.project4.Saver.ImageSaver;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestImageSaver {
    @Test
    public void testSaveMethod_shouldSaveFractalFlameImage(@TempDir Path path) {
        Path imgPath = path.resolve("1.png");
        ImageSaver.save(FractalImage.create(100, 100), imgPath, ImageFormat.PNG);
        Assertions.assertThat(imgPath).exists();
    }
}
