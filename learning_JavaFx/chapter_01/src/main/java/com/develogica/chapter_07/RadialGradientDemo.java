package com.develogica.chapter_07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RadialGradientDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        double WIDTH = 200;
        double HEIGHT = 200;
        var root = new VBox();

        List<Color> colorList = new ArrayList<>();

        var stops = new Stop[]{
                new Stop(0.4, Color.WHITE),
                new Stop(0.6, Color.ORANGE),
                new Stop(0.8, Color.NAVY),
                new Stop(1, Color.DARKBLUE)
        };

        var gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5,
                true, CycleMethod.NO_CYCLE, stops);
        var rectangle1 = new Rectangle(WIDTH, HEIGHT, gradient);
        var rectangle2 = new Rectangle(WIDTH, HEIGHT, Color.ORANGE);

        var buttonExit = new Button("Exit");
        buttonExit.setOnAction(event -> Platform.exit());
        root.getChildren().addAll(rectangle1, rectangle2, buttonExit);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("RadialGradient Demo");
        stage.show();
    }
}
