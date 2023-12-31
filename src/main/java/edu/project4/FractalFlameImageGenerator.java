package edu.project4;

import edu.project4.Elements.FractalImage;
import edu.project4.Elements.ImageFormat;
import edu.project4.Elements.Rectangle;
import edu.project4.Processor.ImageProcessor;
import edu.project4.Renderers.AbstractRenderer;
import edu.project4.Saver.ImageSaver;
import edu.project4.Transformations.Transformation;
import java.nio.file.Path;
import java.util.List;

public final class FractalFlameImageGenerator {
    private final FractalImage fractalImage;
    private final AbstractRenderer renderer;
    private final Rectangle area;
    private final List<Transformation> transformations;
    private final ImageProcessor processor;

    public FractalFlameImageGenerator(
        int width,
        int height,
        AbstractRenderer renderer,
        Rectangle area,
        List<Transformation> transformations,
        ImageProcessor processor
    ) {
        this.fractalImage = FractalImage.create(width, height);
        this.renderer = renderer;
        this.area = area;
        this.transformations = transformations;
        this.processor = processor;
    }

    public void generate(int samples, int iterationsPerSample, int symmetry, Path path, ImageFormat format) {
        renderer.render(fractalImage, area, transformations, samples, iterationsPerSample, symmetry);
        processor.process(fractalImage);
        ImageSaver.save(fractalImage, path, format);
    }
}
