package edu.project4.Processor;

import edu.project4.Elements.FractalImage;
import edu.project4.Elements.Pixel;
import java.util.Objects;

public class GammaCorrectionImageProcessor implements ImageProcessor {
    private static final double GAMMA_PARAMETER_VALUE = 1.9;

    @Override
    public void process(FractalImage image) {
        double maxNormal = 0;

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                Pixel pixel = image.getPixel(x, y);

                if (Objects.requireNonNull(pixel).getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    maxNormal = Math.max(maxNormal, pixel.getNormal());
                }
            }
        }

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                Pixel pixel = image.getPixel(x, y);

                Objects.requireNonNull(pixel).setNormal(pixel.getNormal() / maxNormal);
                pixel.setR(
                    (int) (pixel.getR() * Math.pow(pixel.getNormal(), (1.0 / GAMMA_PARAMETER_VALUE)))
                );
                pixel.setG(
                    (int) (pixel.getG() * Math.pow(pixel.getNormal(), (1.0 / GAMMA_PARAMETER_VALUE)))
                );
                pixel.setB(
                    (int) (pixel.getB() * Math.pow(pixel.getNormal(), (1.0 / GAMMA_PARAMETER_VALUE)))
                );
            }
        }
    }
}
