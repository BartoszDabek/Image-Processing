package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    private Image image;
    private WritableImage writableImage;

    @FXML
    private ImageView imageView;
    private int[][][] rgbBuffer;

    @FXML
    void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            image = new Image(selectedFile.toURI().toString());
            setNewImage();
        }
    }

    @FXML
    void reset() {
        setNewImage();
    }

    private void setNewImage() {
        writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        rgbBuffer = new int[3][(int) image.getWidth()][(int) image.getHeight()];
        PixelReader pixelReader = image.getPixelReader();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = pixelReader.getColor(i, j);
                writableImage.getPixelWriter().setColor(i, j, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void smoothFilter() {
        new SmoothFilter(writableImage, rgbBuffer).execute();
    }
}
