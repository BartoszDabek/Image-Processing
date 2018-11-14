package sample.filter;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianFilter {

    private final WritableImage writableImage;

    public MedianFilter(WritableImage writableImage) {
        this.writableImage = writableImage;
    }

    public void execute() {
        for (int row = 1; row < writableImage.getWidth() - 1; row++) {
            for (int col = 1; col < writableImage.getHeight() - 1; col++) {

                List<Integer> reds = new ArrayList<>();
                List<Integer> greens = new ArrayList<>();
                List<Integer> blues = new ArrayList<>();

                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        Color color = writableImage.getPixelReader().getColor(i, j);
                        reds.add((int) (color.getRed() * 255));
                        greens.add((int) (color.getGreen() * 255));
                        blues.add((int) (color.getBlue() * 255));
                    }
                }

                Collections.sort(reds);
                Collections.sort(greens);
                Collections.sort(blues);

                writableImage.getPixelWriter().setColor(row, col, Color.rgb(reds.get(4), greens.get(4), blues.get(4)));
            }
        }
    }
}
