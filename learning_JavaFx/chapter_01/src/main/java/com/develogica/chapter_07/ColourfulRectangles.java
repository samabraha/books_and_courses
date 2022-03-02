package com.develogica.chapter_07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColourfulRectangles extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        double WIDTH = 300;
        double HEIGHT = 150;

        var stops = new Stop[]{
                new Stop(.45, Color.color(1.0, 0.5, 0.0)),
                new Stop(.55, Color.color(.2, 1, 0.6))};
        var linearGradient = new LinearGradient(0.0, 0.5, 1.0, 0.5,
                true, CycleMethod.REPEAT, stops);


        var rectangle1 = new Rectangle(WIDTH, HEIGHT, linearGradient);
//        stops = new Stop[]{
//                new Stop(0, Color.color(1.0, 0.5, 0.0)),
//                new Stop(1, Color.color(.2, 1, 0.6))};

        final int SIZE = 20;
        List<Stop> stopList = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            stopList.add(new Stop((double) i / SIZE, Color.color(0.8, Math.random(), 0.5)));
        }

        linearGradient = new LinearGradient(0.0, 0.5, 1.0, 0.5,
                true, CycleMethod.NO_CYCLE, stopList);

        var rectangle2 = new Rectangle(WIDTH, HEIGHT, linearGradient);

        stops = new Stop[]{
                new Stop(0, Color.color(0.5, .3, 1)),
                new Stop(1, Color.color(1, 0.5, 0.6))};
        linearGradient = new LinearGradient(0, 0, .5, 5,
                true, CycleMethod.NO_CYCLE, stops);

        var rectangle3 = new Rectangle(WIDTH, HEIGHT, linearGradient);

        stops = new Stop[]{
                new Stop(0, Color.color(0.5, 0.8, 0.4)),
                new Stop(1, Color.color(1, 0.5, 0.6))};
        linearGradient = new LinearGradient(0, 0, .5, 5,
                true, CycleMethod.NO_CYCLE, stops);

        var rectangle4 = new Rectangle(WIDTH, HEIGHT, linearGradient);

        var buttonRecolor = new Button("Recolor");
        buttonRecolor.setOnAction(event -> rectangle4.setFill(Color.RED));
        var buttonExit = new Button("Exit");
        buttonExit.setOnAction(event -> Platform.exit());

        var controlsHBox = new HBox(buttonRecolor, buttonExit);

        var root = new VBox();
        root.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4, controlsHBox);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Colourful Rectangles");
        stage.show();
    }

}
