package sample;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

class SmoothFilter {

    private WritableImage writableImage;
    private int[][][] rgbBuffer;

    SmoothFilter(WritableImage writableImage, int[][][] rgbBuffer) {
        this.writableImage = writableImage;
        this.rgbBuffer = rgbBuffer;
    }

    void execute() {
        for (int row = 1; row < writableImage.getWidth() - 1; row++) {
            for (int col = 1; col < writableImage.getHeight() - 1; col++) {
                Color color = writableImage.getPixelReader().getColor(row, col);
                int red = (int) (color.getRed() * 255);
                int green = (int) (color.getGreen() * 255);
                int blue = (int) (color.getBlue() * 255);

                rgbBuffer[0][row][col] = red;
                rgbBuffer[1][row][col] = green;
                rgbBuffer[2][row][col] = blue;


                int r = rgbBuffer[0][row - 1][col - 1] +
                        rgbBuffer[0][row - 1][col] +
                        rgbBuffer[0][row - 1][col + 1] +

                        rgbBuffer[0][row][col - 1] +
                        rgbBuffer[0][row][col] +
                        rgbBuffer[0][row][col + 1] +

                        rgbBuffer[0][row + 1][col - 1] +
                        rgbBuffer[0][row + 1][col] +
                        rgbBuffer[0][row + 1][col + 1];

                int g = rgbBuffer[1][row - 1][col - 1] +
                        rgbBuffer[1][row - 1][col] +
                        rgbBuffer[1][row - 1][col + 1] +

                        rgbBuffer[1][row][col - 1] +
                        rgbBuffer[1][row][col] +
                        rgbBuffer[1][row][col + 1] +

                        rgbBuffer[1][row + 1][col - 1] +
                        rgbBuffer[1][row + 1][col] +
                        rgbBuffer[1][row + 1][col + 1];

                int b = rgbBuffer[2][row - 1][col - 1] +
                        rgbBuffer[2][row - 1][col] +
                        rgbBuffer[2][row - 1][col + 1] +

                        rgbBuffer[2][row][col - 1] +
                        rgbBuffer[2][row][col] +
                        rgbBuffer[2][row][col + 1] +

                        rgbBuffer[2][row + 1][col - 1] +
                        rgbBuffer[2][row + 1][col] +
                        rgbBuffer[2][row + 1][col + 1];

                writableImage.getPixelWriter().setColor(row, col, Color.rgb(r / 9, g / 9, b / 9));
            }
        }
    }
}
