package com.develogica.util;

import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

import static com.develogica.util.Utils.betweenInclusive;

public class ColorUtil {

    public static Color getRandomColor() {
        double r = ThreadLocalRandom.current().nextDouble();
        double g = ThreadLocalRandom.current().nextDouble();
        double b = ThreadLocalRandom.current().nextDouble();
        return Color.color(r, g, b);
    }

    public static Color getRandomColorForBrightness(int brightnessPercent) {
        double brightness;
        if (betweenInclusive(brightnessPercent, 0, 100)) {
            brightness = brightnessPercent / 100.0;
        } else {
            brightness = Math.abs(brightnessPercent % 100.0) / 100.0;
        }

        var hue = ThreadLocalRandom.current().nextInt(360 + 1);
        var saturation = ThreadLocalRandom.current().nextInt(100 + 1) / 100.0;
        return Color.hsb(hue, saturation, brightness);
    }

    public static Color getRandomColorForSaturation(int saturationPercent) {
        double saturation;
        if (betweenInclusive(saturationPercent, 0, 100)) {
            saturation = saturationPercent / 100.0;
        } else {
            saturation = Math.abs(saturationPercent % 100.0) / 100.0;
        }

        var hue = ThreadLocalRandom.current().nextInt(360 + 1);
        var brightness = ThreadLocalRandom.current().nextInt(100 + 1) / 100.0;
        return Color.hsb(hue, saturation, brightness);
    }

    /**
     * Creates a css rgb function for a random number
     */
    public static String getRandomColorCssRgbFunction() {
        Color color = getRandomColor();
        var r = (int) (color.getRed() * 255);
        var g = (int) (color.getGreen() * 255);
        var b = (int) (color.getBlue() * 255);
        return "rgb(%d, %d, %d)".formatted(r, g, b);
    }
}

