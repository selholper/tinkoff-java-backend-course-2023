package edu.project4.Saver;

import edu.project4.Elements.FractalImage;
import edu.project4.Elements.ImageFormat;
import edu.project4.Elements.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Objects;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageSaver {
    @SneakyThrows
    public static void save(FractalImage image, Path path, ImageFormat imageFormat) {
        BufferedImage bufferedImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(Objects.requireNonNull(pixel).getR(), pixel.getG(), pixel.getB());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        ImageIO.write(bufferedImage, imageFormat.name(), path.toFile());
    }
}
