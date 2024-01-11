package edu.project4.Renderers;

import edu.project4.Elements.AffineCoefficients;
import edu.project4.Elements.FractalImage;
import edu.project4.Elements.Pixel;
import edu.project4.Elements.Point;
import edu.project4.Elements.Rectangle;
import edu.project4.Transformations.Transformation;
import java.util.List;

public abstract class AbstractRenderer {
    protected static final int INCOMPLETE_STEPS = 30;

    public abstract FractalImage render(
        FractalImage image,
        Rectangle area,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetry
    );

    protected Point getRotatedPoint(Point pw, double theta) {
        double xRotated = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double yRotated = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);

        return new Point(xRotated, yRotated);
    }

    protected void setPixelColor(Pixel pixel, AffineCoefficients coefficients) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(coefficients.color().getRed());
            pixel.setG(coefficients.color().getGreen());
            pixel.setB(coefficients.color().getBlue());
        } else {
            pixel.setR((pixel.getR() + coefficients.color().getRed()) / 2);
            pixel.setG((pixel.getG() + coefficients.color().getGreen()) / 2);
            pixel.setB((pixel.getB() + coefficients.color().getBlue()) / 2);
        }
    }

    protected AffineCoefficients[] getRandomAffineCoefficients(int samples) {
        AffineCoefficients[] transformations = new AffineCoefficients[samples];

        for (int i = 0; i < samples; ++i) {
            transformations[i] = AffineCoefficients.create();
        }

        return transformations;
    }

    protected Point getPointAfterAffineTransformation(AffineCoefficients coefficients, Point pw) {
        double xTransformed = getXAfterAffineTransformation(coefficients, pw);
        double yTransformed = getYAfterAffineTransformation(coefficients, pw);

        return new Point(xTransformed, yTransformed);
    }

    private double getXAfterAffineTransformation(AffineCoefficients coefficients, Point point) {
        return coefficients.a() * point.x() + coefficients.b() * point.y() + coefficients.c();
    }

    private double getYAfterAffineTransformation(AffineCoefficients coefficients, Point point) {
        return coefficients.d() * point.x() + coefficients.e() * point.y() + coefficients.f();
    }
}
