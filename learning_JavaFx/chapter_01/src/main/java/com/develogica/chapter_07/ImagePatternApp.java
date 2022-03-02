package com.develogica.chapter_07;

import com.develogica.util.ResourceUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ImagePatternApp extends Application {
    private Image img;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        final String imgPath = ResourceUtil.getResourceURLString("picture/blue_rounded_rect.png");
        img = new Image(imgPath);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ImagePattern pattern1 = new ImagePattern(img, 0, 0, 0.25, 0.25, false);
        Rectangle rectangle1 = new Rectangle(100, 50);
        rectangle1.setFill(pattern1);

        ImagePattern pattern2 = new ImagePattern(img, 0, 0, 0, 0, false);
        Rectangle rectangle2 = new Rectangle(100, 50);
        rectangle2.setFill(pattern2);

        ImagePattern pattern3 = new ImagePattern(img, 0, 0, 0, 0, false);
        Rectangle rectangle3 = new Rectangle(100, 50);
        rectangle3.setFill(pattern3);

        ImagePattern pattern4 = new ImagePattern(img, 0, 0, 0, 0, false);
        Circle circle = new Circle(50, 50, 25);
        circle.setFill(pattern4);

        var root = new HBox();
        root.getChildren().addAll(rectangle1, rectangle2, rectangle3, circle);
        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Using Image Patterns");
        stage.show();
    }
}
