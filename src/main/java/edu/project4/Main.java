package edu.project4;

import edu.project4.Elements.ImageFormat;
import edu.project4.Elements.Rectangle;
import edu.project4.Processor.GammaCorrectionImageProcessor;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Transformations.ExTransformation;
import edu.project4.Transformations.LinearTransformation;
import edu.project4.Transformations.TangentTransformation;
import edu.project4.Transformations.Transformation;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Main {
    private static final String PATH = "D:\\Projects\\FintechTinkoff"
        + "\\tinkoff-java-backend-course-2023\\src\\main\\java\\edu\\project4\\Images\\";

    private Main() {
    }

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
    public static void main(String[] args) {
        List<Transformation> transformations;
        transformations = new ArrayList<>(
            Arrays.asList(
                new LinearTransformation(),
                new ExTransformation(),
                new TangentTransformation()
            )
        );

        FractalFlameImageGenerator fractalFlameImageGenerator = new FractalFlameImageGenerator(
           1920,
           1080,
           new MultiThreadRenderer(),
           new Rectangle(-4, -2.25, 8, 4.5),
           transformations,
           new GammaCorrectionImageProcessor()
       );

       fractalFlameImageGenerator.generate(
            7,
           10000000,
           1,
           Paths.get(PATH + "16.png"),
           ImageFormat.PNG
       );
    }
}
