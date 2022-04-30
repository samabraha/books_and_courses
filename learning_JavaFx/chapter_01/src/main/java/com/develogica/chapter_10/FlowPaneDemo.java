package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.controlsfx.control.SegmentedBar;
import static org.controlsfx.control.SegmentedBar.Segment;

import java.util.concurrent.ThreadLocalRandom;

public class FlowPaneDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SegmentedBar<Segment> segmentedBar = new SegmentedBar<>();
//        segmentedBar.setOrientation(Orientation.HORIZONTAL);

        segmentedBar.getSegments().addAll(
                new Segment(10, "10"),
                new Segment(10, "10"),
                new Segment(10, "10"),
                new Segment(10, "10"),
                new Segment(10, "10"),
                new Segment(50, "50")
        );


        var flowPane = new FlowPane( 5, 10);
        flowPane.setAlignment(Pos.BOTTOM_CENTER);
        var addButton = new Button("add");
        addButton.setOnAction(event -> addButton(flowPane));

        var removeButton = new Button("remove");
        removeButton.setOnAction(event -> removeButton(flowPane));

        var clearButton = new Button("clear");
        clearButton.setOnAction(event -> clearButton(flowPane));

        var exitButton = new Button("exit");
        exitButton.setOnAction(event -> Platform.exit());

        var buttonsVBox = new VBox(10, addButton, removeButton, clearButton, exitButton);

        var root = new HBox(flowPane, buttonsVBox, segmentedBar);
        root.setAlignment(Pos.CENTER);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FlowPane Demo");
        stage.show();
    }

    private void clearButton(FlowPane pane) {
        pane.getChildren().removeAll(pane.getChildren());
    }

    private void removeButton(FlowPane pane) {
        pane.getChildren().remove(pane.getChildren().size() - 1);
    }

    private void addButton(FlowPane pane) {
        var number = pane.getChildren().size();
        var button = new Button("Button " + number);
        pane.getChildren().add(button);
        button.setOnAction(event -> setBackgroundColor(button, number));
    }

    private void setBackgroundColor(Button button, int number) {

            var redVal = ThreadLocalRandom.current().nextInt(256);
//        var redVal = number * 10 % 256;
            var greenVal = ThreadLocalRandom.current().nextInt(256);
//        var greenVal = number * 25 % 256;
        var blueVal = ThreadLocalRandom.current().nextInt(256);

        var color = Color.rgb(redVal, greenVal, blueVal);
        var fontColorString = color.getBrightness() > .6 ? "black" : "white";

         var styleString = """
                    -fx-text-fill: %s;
                    -fx-background-insets: 5;
                    -fx-background-radius: 10;
                    -fx-font-size: 20;
                    -fx-background-color: rgb(%d, %d, %d);
                    """.formatted(fontColorString, redVal, greenVal, blueVal);

        button.setStyle(styleString);
    }
}

