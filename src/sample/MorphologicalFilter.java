package sample;

import javafx.scene.image.WritableImage;

abstract class MorphologicalFilter {

    final WritableImage writableImage;
    final int height;
    final int width;
    static final int mask[][] = new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
    };

    MorphologicalFilter(WritableImage writableImage) {
        this.writableImage = writableImage;
        width = (int) writableImage.getWidth();
        height = (int) writableImage.getHeight();
    }

}
