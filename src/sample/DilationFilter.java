package sample;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

class DilationFilter {

    private final WritableImage writableImage;
    private static final int mask[][] = new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
    };

    DilationFilter(WritableImage writableImage) {
        this.writableImage = writableImage;
    }

    void execute() {
        for (int row = 0; row < writableImage.getWidth(); row++) {
            for (int col = 0; col < writableImage.getHeight(); col++) {

                Color color = writableImage.getPixelReader().getColor(row, col);
                int bw = getGrayScale(color);

                if (bw <= 127) {
                    bw = 0;
                } else {
                    bw = 255;
                }

                writableImage.getPixelWriter().setColor(row, col, Color.rgb(bw, bw, bw));
            }
        }

        grayscaleImage(new int[] {1, 1, 1,
                                  1, 1, 1,
                                  1, 1, 1},
                3);
    }

    private int getGrayScale(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        return (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
    }

    private void grayscaleImage(int mask[], int maskSize){
        int width = (int) writableImage.getWidth();
        int height = (int) writableImage.getHeight();

        Set<Color> colors;
        Color output[][] = new Color[width][height];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                colors = new HashSet<>();
                for(int ty = i - maskSize/2, mr = 0; ty <= i + maskSize/2; ty++, mr++){
                    for(int tx = j - maskSize/2, mc = 0; tx <= j + maskSize/2; tx++, mc++){

                        if(ty >= 0 && ty < height && tx >= 0 && tx < width){

                            if(mask[mc+mr*maskSize] != 1){
                                continue;
                            }

                            colors.add(writableImage.getPixelReader().getColor(tx, ty));
                        }
                    }
                }

                if (colors.contains(Color.WHITE)) {
                    output[j][i] = Color.WHITE;
                } else {
                    output[j][i] = Color.BLACK;
                }
            }
        }

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Color color = output[j][i];
                writableImage.getPixelWriter().setColor(j, i, color);
            }
        }
    }
}
