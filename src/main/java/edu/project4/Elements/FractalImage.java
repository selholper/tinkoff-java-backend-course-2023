package edu.project4.Elements;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

public final class FractalImage {
    private final Pixel[][] image;
    @Getter
    private final int width;
    @Getter
    private final int height;


    private FractalImage(Pixel[][] image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public static FractalImage create(int width, int height) {
        Pixel[][] image = new Pixel[width][height];

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                image[x][y] = new Pixel(0, 0, 0, 0, 0);
            }
        }

        return new FractalImage(image, width, height);
    }

    public boolean isCorrectPixelCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Nullable
    public Pixel getPixel(int x, int y) {
        return isCorrectPixelCoordinate(x, y) ? image[x][y] : null;
    }
}
