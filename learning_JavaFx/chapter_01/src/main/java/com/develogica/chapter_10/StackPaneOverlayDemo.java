package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackPaneOverlayDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var textOverRect = createStackPane("Hello", 1.0, true);
        var rectOverText = createStackPane("Hello", 1.0, false);
        var transparentRectOverText = createStackPane("Hello", 0.5, false);
        var rectOverBigText = createStackPane("Hello World from JavaFX", 1.0, false);
        var transparentRectOverBigText = createStackPane("Hello World from JavaFX", 0.5, false);

        var root = new HBox(textOverRect, rectOverText, transparentRectOverText,
                rectOverBigText, transparentRectOverBigText);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("StackPane Overlay Demo");
        stage.show();
    }

    private StackPane createStackPane(String message, double opacity, boolean rectFirst) {
        var rect = new Rectangle(60, 50);
        rect.setStyle("""
                -fx-fill: teal;
                -fx-opacity: %f;
                """.formatted(opacity));

        var text = new Text(message);
        var stackPane = new StackPane();

        if (rectFirst) {
            stackPane.getChildren().addAll(rect, text);
        } else {
            stackPane.getChildren().addAll(text, rect);
        }

        stackPane.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: teal;
                """);

        return stackPane;
    }
}

