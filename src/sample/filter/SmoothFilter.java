package sample.filter;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SmoothFilter {

    private final WritableImage newImage;

    public SmoothFilter(WritableImage writableImage) {
        newImage = writableImage;
    }

    public void execute() {
        for (int x = 1; x < newImage.getWidth() - 1; x++) {
            for (int y = 1; y < newImage.getHeight() - 1; y++) {

                double red = 0;
                double green = 0;
                double blue = 0;

                /*
                    A1|A2|A3
                    --------
                    B1|B2|B3
                    --------
                    C1|C2|C3
                 */

                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        Color color = newImage.getPixelReader().getColor(i, j);
                        red += color.getRed();
                        green += color.getGreen();
                        blue += color.getBlue();
                    }
                }

                newImage.getPixelWriter().setColor(x, y, Color.color(red / 9, green / 9, blue / 9));
            }
        }
    }
}
