package com.develogica.chapter_07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.develogica.chapter_07.ColourfulRectangles.getRandomColor;

public class RadialGradientDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final double WIDTH = 200;
        final double HEIGHT = 100;
        var root = new VBox();
        root.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(10, 200, 15, .5), null, null)));

        int SIZE = 6 - 1;
        final var stops = new ArrayList<Stop>(SIZE);

        for (int i = 0; i <= SIZE; i++) {
            var stop = new Stop((double) i / SIZE, getRandomColor());
            stops.add(stop);
        }

        var stopsArr = new Stop[]{
                new Stop(0.0, Color.WHITE),
                new Stop(0.33, Color.ORANGE),
                new Stop(0.66, Color.NAVY),
                new Stop(1, Color.DARKBLUE)
        };

        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5,
                true, CycleMethod.NO_CYCLE, stopsArr);
        var rectangle1 = new Rectangle(WIDTH, HEIGHT, gradient);

        var stopList = List.of(
                new Stop(0, Color.YELLOW),
                new Stop(.7, Color.rgb(200, 0, 25, .3)),
                new Stop(1, Color.BLACK));
        gradient = new RadialGradient(315, 0.5, 0.6, 0.4, 0.3,
                false, CycleMethod.NO_CYCLE, stopList);
        var circle1 = new Circle( WIDTH / 2, gradient);

        stopList = List.of(
                new Stop(.2, Color.YELLOW),
                new Stop(.25, Color.ORANGE),
                new Stop(.75, Color.ORANGE),
                new Stop(.8, Color.BLACK));
        gradient = new RadialGradient(0, 0.0, 0.5, 0.5, 0.1,
                true, CycleMethod.REFLECT, stopList);
        var circle2 = new Circle( WIDTH / 2, gradient);

        var buttonRecolor = new Button("Recolor");
        buttonRecolor.setOnAction(event -> {

        });

        var buttonExit = new Button("Exit");
        buttonExit.setOnAction(event -> Platform.exit());
        root.getChildren().addAll(rectangle1, circle1, circle2, buttonRecolor, buttonExit);
        var scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("RadialGradient Demo");
        stage.show();
    }
}
