package edu.project4;

import edu.project4.Elements.ImageFormat;
import edu.project4.Elements.Rectangle;
import edu.project4.Processor.GammaCorrectionImageProcessor;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Transformations.BubbleTransformation;
import edu.project4.Transformations.CosineTransformation;
import edu.project4.Transformations.CylinderTransformation;
import edu.project4.Transformations.DiamondTransformation;
import edu.project4.Transformations.DiscTransformation;
import edu.project4.Transformations.ExTransformation;
import edu.project4.Transformations.ExponentialTransformation;
import edu.project4.Transformations.FisheyeTransformation;
import edu.project4.Transformations.HandkerchiefTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.HorseshoeTransformation;
import edu.project4.Transformations.HyperbolicTransformation;
import edu.project4.Transformations.JuliaTransformation;
import edu.project4.Transformations.LinearTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SinusoidalTransformation;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.SpiralTransformation;
import edu.project4.Transformations.SwirlTransformation;
import edu.project4.Transformations.TangentTransformation;
import java.nio.file.Path;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestFractalFlameImageGenerator {
    @Test
    void testGenerateMethod_shouldGenerateFractalFlameImage(@TempDir Path path) {
        FractalFlameImageGenerator generator = new FractalFlameImageGenerator(
            1920,
            1080,
            new MultiThreadRenderer(),
            new Rectangle(-2, -1, 4, 2),
            List.of(
                new BubbleTransformation(),
                new CosineTransformation(),
                new CylinderTransformation(),
                new DiamondTransformation(),
                new DiscTransformation(),
                new ExponentialTransformation(),
                new ExTransformation(),
                new FisheyeTransformation(),
                new HandkerchiefTransformation(),
                new HeartTransformation(),
                new HorseshoeTransformation(),
                new HyperbolicTransformation(),
                new JuliaTransformation(),
                new LinearTransformation(),
                new PolarTransformation(),
                new SinusoidalTransformation(),
                new SphericalTransformation(),
                new SpiralTransformation(),
                new SwirlTransformation(),
                new TangentTransformation()
            ),
            new GammaCorrectionImageProcessor()
        );

        generator.generate(1, 1, 1, path.resolve("1.jpg"), ImageFormat.JPEG);
        generator.generate(1, 1, 1, path.resolve("1.bmp"), ImageFormat.BMP);
        generator.generate(1, 1, 1, path.resolve("1.png"), ImageFormat.PNG);

        Assertions.assertThat(path.resolve("1.jpg")).exists();
        Assertions.assertThat(path.resolve("1.bmp")).exists();
        Assertions.assertThat(path.resolve("1.png")).exists();
    }
}
