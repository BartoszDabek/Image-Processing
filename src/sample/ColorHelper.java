package sample;

import javafx.scene.paint.Color;

public class ColorHelper {

    private ColorHelper() {

    }

    public static int getGrayScale(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        return (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
    }
}
