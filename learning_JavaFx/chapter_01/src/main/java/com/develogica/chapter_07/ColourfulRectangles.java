package com.develogica.chapter_07;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
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
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ColourfulRectangles extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        double WIDTH = 350;
        double HEIGHT = 150;

        var stops = new Stop[]{
                new Stop(.5, Color.color(1.0, 0.0, 0.0, 0.5)),
                new Stop(.5, Color.color(1.0, 1.0, 1.0))};
        var linearGradient = new LinearGradient(0.0, 0.0, 0.0, 0.2,
                true, CycleMethod.REPEAT, stops);


        var rectangle1 = new Rectangle(WIDTH, HEIGHT, linearGradient);
//        stops = new Stop[]{
//                new Stop(0, Color.color(1.0, 0.5, 0.0)),
//                new Stop(1, Color.color(.2, 1, 0.6))};

        final int SIZE = 50;
        List<Stop> stopList = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            stopList.add(new Stop((double) i / SIZE, Color.color(0.9, Math.random(), 0.5)));
        }

        linearGradient = new LinearGradient(0.0, 0.5, 1.0, 0.5,
                true, CycleMethod.NO_CYCLE, stopList);

        var rectangle2 = new Rectangle(WIDTH, HEIGHT, linearGradient);

        stops = new Stop[]{
                new Stop(0, Color.color(0.5, .3, 1)),
                new Stop(1, Color.color(1, 0.8, 0.6))};
        linearGradient = new LinearGradient(0, 50, 0, 100,
                false, CycleMethod.NO_CYCLE, stops);

        var rectangle3 = new Rectangle(WIDTH, HEIGHT, linearGradient);

        stops = new Stop[]{
                new Stop(0, Color.color(0.5, 0.8, 0.4, 0)),
                new Stop(1, Color.color(1, 0.5, 0.6))};
        linearGradient = new LinearGradient(0, 0, 1, 0,
                true, CycleMethod.NO_CYCLE, stops);

        var rectangle4 = new Rectangle(WIDTH, HEIGHT, linearGradient);

        var buttonRecolor = new Button("Recolor");
        buttonRecolor.setOnAction(event -> rectangle4.setFill(Color.web(gerRandomColorString(), .5)));

        linearGradient = rainbowMe(20);
        var rectangle5 = new Rectangle(WIDTH, HEIGHT, linearGradient);


        var buttonExit = new Button("Exit");
        buttonExit.setOnAction(event -> Platform.exit());

        Button buttonRainbow = new Button("Shuffle Colors");
        buttonRainbow.setOnAction(event -> {
            rectangle5.setFill(rainbowMe(8));
        });

        var controlsHBox = new HBox(buttonRecolor, buttonRainbow, buttonExit);
        
        buttonRecolor.setPrefWidth(WIDTH / 3);
        buttonRainbow.setPrefWidth(WIDTH / 3);
        buttonExit.setPrefWidth(WIDTH / 3);

        var root = new VBox();

        root.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, controlsHBox);
        root.setStyle("-fx-background-color: transparent");

        var scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Colourful Rectangles");
        stage.show();
    }

    private String gerRandomColorString() {
        var r = Integer.toHexString(ThreadLocalRandom.current().nextInt(16));
        var g = Integer.toHexString(ThreadLocalRandom.current().nextInt(16));
        var b = Integer.toHexString(ThreadLocalRandom.current().nextInt(16));
        return String.format("#%s%s%s", r, g, b);
    }

    private static LinearGradient rainbowMe(int numberOfColors) {
        var colorList = new ArrayList<Color>();
        while (colorList.size() < numberOfColors) {
            colorList.add(getRandomColor());
        }

        Collections.shuffle(colorList);

        var listSize = colorList.size();
        var stopList = new Stop[listSize];

        for (int i = 0; i < listSize; i++) {
            var sentinel = 1.0 / (listSize - 1) * i;
            stopList[i] = (new Stop(sentinel, colorList.get(i)));
        }


        return new LinearGradient(0, 0, 1, 0,
                true, CycleMethod.NO_CYCLE,  stopList);
    }

    public static Color getRandomColor() {
        double r = ThreadLocalRandom.current().nextDouble();
        double g = ThreadLocalRandom.current().nextDouble();
        double b = ThreadLocalRandom.current().nextDouble();
        return Color.color(r, g, b);
    }
}
