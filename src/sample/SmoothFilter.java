package sample;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

class SmoothFilter {

    private WritableImage writableImage;

    SmoothFilter(WritableImage writableImage) {
        this.writableImage = writableImage;
    }

    void execute() {
        for (int row = 1; row < writableImage.getWidth() - 1; row++) {
            for (int col = 1; col < writableImage.getHeight() - 1; col++) {

                int red = 0;
                int green = 0;
                int blue = 0;

                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        Color color = writableImage.getPixelReader().getColor(i, j);
                        red += (int) (color.getRed() * 255);
                        green += (int) (color.getGreen() * 255);
                        blue += (int) (color.getBlue() * 255);
                    }
                }

                writableImage.getPixelWriter().setColor(row, col, Color.rgb(red / 9, green / 9, blue / 9));
            }
        }
    }
}
