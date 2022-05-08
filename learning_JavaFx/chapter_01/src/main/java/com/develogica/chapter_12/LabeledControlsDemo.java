package com.develogica.chapter_12;

import com.develogica.util.ColorUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;


public class LabeledControlsDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var labelsFlowPane = new FlowPane();
        labelsFlowPane.setPadding(new Insets(15));
        labelsFlowPane.setHgap(10);
        labelsFlowPane.setVgap(10);
        labelsFlowPane.setPrefSize(600, 400);

        var styleStr = """
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 5;
                -fx-border-color: %s;
                -fx-font-size: 20;
                -fx-background-color: %s;
                """;

        var randomHue = ThreadLocalRandom.current().nextInt(360 + 1);

        for (int i = 1; i <= 20; i++) {
            var hueRange = ThreadLocalRandom.current().nextInt() % 30;

            var color = ColorUtil.getRandomColorForHue(randomHue + hueRange);
            var borderColorStr = ColorUtil.getCssRgbFunctionString(
                    color);

            var backgroundColorStr = ColorUtil.getCssRgbFunctionString(
                    color.invert());

            var label = new Label();
            label.setTextFill(color);
            label.setStyle(styleStr.formatted(borderColorStr, backgroundColorStr));
            label.setText(ColorUtil.getCssRgbFunctionString(color));

            labelsFlowPane.getChildren().addAll(label);
        }

        var aboutButton = new Button("About");
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        var exitButton = new Button("Exit");
        exitButton.setMaxWidth(Double.MAX_VALUE);

        var buttonVBox = new VBox(10, aboutButton, exitButton);

        var root = new HBox(10, labelsFlowPane, buttonVBox);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Labeled Controls Demo");
        stage.show();
    }
}

