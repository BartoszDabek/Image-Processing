package sample;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

import static sample.ColorHelper.getIntFromColor;

abstract class MorphologicalFilter {

    Map<Integer, Color> map;
    private final WritableImage writableImage;
    private final int height;
    private final int width;
    private static final int mask[][] = new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
    };

    MorphologicalFilter(WritableImage writableImage) {
        this.writableImage = writableImage;
        width = (int) writableImage.getWidth();
        height = (int) writableImage.getHeight();
    }

    void execute() {
        int padding = (mask.length - 1) / 2;
        Color output[][] = new Color[width][height];

        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {

                map = new HashMap<>();

                for (int i = -padding; i <= padding; i++) {
                    for (int j = -padding; j <= padding; j++) {

                        if (mask[padding + i][padding + j] == 1) {
                            Color color = writableImage.getPixelReader().getColor(col + i, row + j);
                            int intFromColor = getIntFromColor(color.getRed(), color.getGreen(), color.getBlue());
                            map.putIfAbsent(intFromColor, color);
                        }
                    }
                }

                Integer key = getColorKey();
                Color color = map.get(key);
                output[col][row] = color;
            }
        }

        writeImage(output);
    }

    protected abstract Integer getColorKey();

    private void writeImage(Color[][] output) {
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                Color color = output[j][i];
                writableImage.getPixelWriter().setColor(j, i, color);
            }
        }
    }

}
