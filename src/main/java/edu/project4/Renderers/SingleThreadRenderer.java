package edu.project4.Renderers;

import edu.project4.Elements.AffineCoefficients;
import edu.project4.Elements.FractalImage;
import edu.project4.Elements.Pixel;
import edu.project4.Elements.Point;
import edu.project4.Elements.Rectangle;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadRenderer extends AbstractRenderer {
    @Override
    public FractalImage render(
        FractalImage image,
        Rectangle area,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetry
    ) {
        AffineCoefficients[] randomCoefficientsArray = getRandomAffineCoefficients(samples);

        for (int i = 0; i < samples; ++i) {
            Point point = area.getRandomPoint();
            for (int j = -INCOMPLETE_STEPS; j < iterationsPerSample; ++j) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, randomCoefficientsArray.length);
                AffineCoefficients randomCoefficients = randomCoefficientsArray[randomIndex];
                point = getPointAfterAffineTransformation(randomCoefficients, point);
                Transformation transformation = transformations.get(
                    ThreadLocalRandom.current().nextInt(0, transformations.size())
                );
                point = transformation.apply(point);

                if (j > -1) {
                    double theta = 0;
                    for (int k = 0; k < symmetry; ++k) {
                        theta += 2 * Math.PI / symmetry;
                        Point rotatedPoint = getRotatedPoint(point, theta);

                        if (area.doesNotContainPoint(rotatedPoint)) {
                            continue;
                        }

                        Pixel pixel = image.getPixel(
                            (int) ((rotatedPoint.x() - area.x()) * image.getWidth() / area.width()),
                            (int) ((rotatedPoint.y() - area.y()) * image.getHeight() / area.height())
                        );

                        if (pixel == null) {
                            continue;
                        }

                        setPixelColor(pixel, randomCoefficients);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }

        return image;
    }
}
