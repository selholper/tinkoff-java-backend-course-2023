package edu.project4.Renderers;

import edu.project4.Elements.AffineCoefficients;
import edu.project4.Elements.FractalImage;
import edu.project4.Elements.Pixel;
import edu.project4.Elements.Point;
import edu.project4.Elements.Rectangle;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class MultiThreadRenderer extends AbstractRenderer {
    @Override
    @SneakyThrows
    public FractalImage render(
        FractalImage image,
        Rectangle area,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        // We get the number of available threads to ensure performance
        int availableThreadsNumber = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(availableThreadsNumber);
        AffineCoefficients[] coefficients = getRandomAffineCoefficients(samples);

        for (int i = 0; i < samples; ++i) {
            executorService.execute(() -> executeIterations(
                image,
                area,
                iterationsPerSample,
                symmetry,
                transformations,
                coefficients
            ));
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS)) {
            throw new RuntimeException("Cannot await termination");
        }

        return image;
    }

    @SneakyThrows
    private void executeIterations(
        FractalImage canvas,
        Rectangle area,
        int iterationsPerSample,
        int symmetry,
        List<Transformation> transformations,
        AffineCoefficients[] coefficientsArray
    ) {
        Point point = area.getRandomPoint();
        for (int i = -INCOMPLETE_STEPS; i < iterationsPerSample; ++i) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, coefficientsArray.length);
            AffineCoefficients coefficients = coefficientsArray[randomIndex];
            point = getPointAfterAffineTransformation(coefficients, point);
            Transformation transformation =
                transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size()));
            point = transformation.apply(point);

            if (i > -1) {
                double theta = 0;

                for (int s = 0; s < symmetry; ++s) {
                    theta += 2 * Math.PI / symmetry;
                    Point rotatedPoint = getRotatedPoint(point, theta);

                    if (area.doesNotContainPoint(rotatedPoint)) {
                        continue;
                    }

                    Pixel pixel = canvas.getPixel(
                        (int) ((rotatedPoint.x() - area.x()) * canvas.getWidth() / area.width()),
                        (int) ((rotatedPoint.y() - area.y()) * canvas.getHeight() / area.height())
                    );

                    if (pixel == null) {
                        continue;
                    }

                    synchronized (pixel) {
                        setPixelColor(pixel, coefficients);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
    }
}
