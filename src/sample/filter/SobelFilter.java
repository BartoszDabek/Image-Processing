package sample.filter;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static sample.util.ColorHelper.getGrayScale;

public class SobelFilter {

    private final WritableImage writableImage;
    private static final int gX[][] = new int[][]{
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
    };

    private static final int gY[][] = new int[][]{
            {-1, -2, -1},
            { 0,  0,  0},
            { 1,  2,  1}
    };

    public SobelFilter(WritableImage writableImage) {
        this.writableImage = writableImage;
    }

    public void execute() {
        int[][] edgeColors = new int[(int) writableImage.getWidth()][(int) writableImage.getHeight()];
        int maxGradient = -1;
        int paddingX = (gX.length - 1) / 2;
        int paddingY = (gY.length - 1) / 2;

        for (int row = 1; row < writableImage.getWidth() - 1; row++) {
            for (int col = 1; col < writableImage.getHeight() - 1; col++) {

                int gx = 0;
                int gy = 0;

                for (int i = -paddingX; i <= paddingX; i++) {
                    for (int j = -paddingY; j <= paddingY; j++) {
                        Color color = writableImage.getPixelReader().getColor(row + i, col + j);
                        int grayColor = getGrayScale(color);

                        gx += gX[paddingX + i][paddingX + j] * grayColor;
                        gy += gY[paddingY + i][paddingY + j] * grayColor;
                    }
                }

                double gval = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gval;

                if (maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[row][col] = g;
            }
        }

        double scale = 255.0 / maxGradient;
        writeImage(edgeColors, scale);
    }

    private void writeImage(int[][] edgeColors, double scale) {
        for (int i = 0; i < writableImage.getWidth(); i++) {
            for (int j = 0; j < writableImage.getHeight(); j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int) (edgeColor * scale);

                writableImage.getPixelWriter().setColor(i, j, Color.rgb(edgeColor, edgeColor, edgeColor));
            }
        }
    }

}
