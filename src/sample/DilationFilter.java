package sample;

import javafx.scene.image.WritableImage;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

class DilationFilter extends MorphologicalFilter {

    DilationFilter(WritableImage writableImage) {
        super(writableImage);
    }

    @Override
    protected Integer getColorKey() {
        return Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getKey)).getKey();
    }
}
