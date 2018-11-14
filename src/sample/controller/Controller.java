package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import sample.filter.MedianFilter;
import sample.filter.SmoothFilter;
import sample.filter.SobelFilter;
import sample.filter.morphological.DilationFilter;
import sample.filter.morphological.ErosionFilter;

import java.io.File;

public class Controller {

    private Image image;
    private WritableImage writableImage;

    @FXML
    private ImageView imageView;

    @FXML
    void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
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
        new SmoothFilter(writableImage).execute();
    }

    @FXML
    void medianFilter() {
        new MedianFilter(writableImage).execute();
    }

    @FXML
    void sobelFilter() {
        new SobelFilter(writableImage).execute();
    }

    @FXML
    void dilationFilter() {
        new DilationFilter(writableImage).execute();
    }

    @FXML
    void erosionFilter() {
        new ErosionFilter(writableImage).execute();
    }
}
