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

    public static int getIntFromColor(double Red, double Green, double Blue){
        int R = (int) Math.round(255 * Red);
        int G = (int) Math.round(255 * Green);
        int B = (int) Math.round(255 * Blue);

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }
}
