package edu.project4;

import edu.project4.Elements.FractalImage;
import edu.project4.Elements.Rectangle;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.BubbleTransformation;
import edu.project4.Transformations.CosineTransformation;
import edu.project4.Transformations.CylinderTransformation;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRenderers {
    @Test
    void testSingleThreadRenderer_shouldNotThrowAnyExceptionForCorrectVariables() {
        Assertions.assertDoesNotThrow(
            () -> new SingleThreadRenderer().render(
                FractalImage.create(100, 100),
                new Rectangle(-2, -1, 4, 2),
                List.of(
                   new BubbleTransformation(),
                   new CosineTransformation(),
                   new CylinderTransformation()
                ),
                1920,
                1080,
                1
            )
        );
    }

    @Test
    void testMultiThreadRenderer_shouldNotThrowAnyExceptionForCorrectVariables() {
        Assertions.assertDoesNotThrow(
            () -> new MultiThreadRenderer().render(
                FractalImage.create(100, 100),
                new Rectangle(-2, -1, 4, 2),
                List.of(
                    new BubbleTransformation(),
                    new CosineTransformation(),
                    new CylinderTransformation()
                ),
                1920,
                1080,
                1
            )
        );
    }
}
