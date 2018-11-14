package sample;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Arrays;

import static sample.ColorHelper.getGrayScale;

class DilationFilter extends MorphologicalFilter {

    DilationFilter(WritableImage writableImage) {
        super(writableImage);
    }

    void execute() {
        int padding = (mask.length - 1) / 2;
        int[] colorsTest;
        int output[][] = new int[width][height];

        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {

                colorsTest = new int[9];
                int counter = 0;
                for (int i = -padding; i <= padding; i++) {
                    for (int j = -padding; j <= padding; j++) {

                        if (mask[padding + i][padding + j] == 1) {
                            int grayScale = getGrayScale(writableImage.getPixelReader().getColor(col + i, row + j));
                            colorsTest[counter++] = grayScale;
                        }
                    }
                }

                Arrays.sort(colorsTest);
                output[col][row] = colorsTest[8];
            }
        }

        writeImage(output);
    }

    private void writeImage(int[][] output) {
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int color = output[j][i];
                writableImage.getPixelWriter().setColor(j, i, Color.rgb(color, color, color));
            }
        }
    }
}
