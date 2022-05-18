package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIFactory extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var root = new FlowPane();

        EventHandler<ActionEvent> helloHandler = event -> System.out.println("Clicked Hello button");
        EventHandler<ActionEvent> exitHandler = event -> Platform.exit();

        var pane = getButtons(
                new HandlerTextPair(helloHandler, "Hello!"),
                new HandlerTextPair(exitHandler, "Bye"));

        root.getChildren().add(pane);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("GUI Factory Demo");
        stage.show();
    }

    public static Pane getButtons(HandlerTextPair... handlerTextPair) {
        var pane = new FlowPane();

        if (handlerTextPair == null || handlerTextPair.length <= 0) {
            return pane;
        }

        for (var pair : handlerTextPair) {
            var button = new Button(pair.text());
            button.setOnAction(pair.handler());
            button.setMaxWidth(Double.MAX_VALUE);
            pane.getChildren().add(button);
        }

        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        return pane;
    }
}

record HandlerTextPair(EventHandler<ActionEvent> handler, String text) {}

