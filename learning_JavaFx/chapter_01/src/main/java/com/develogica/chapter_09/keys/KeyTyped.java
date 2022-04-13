package com.develogica.chapter_09.keys;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KeyTyped extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var nameLabel = new Label();

        var nameTField = new TextField();
        nameTField.setOnKeyTyped(this::handleKeyTypedEvent);

        var root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(nameLabel, nameTField);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("KeyTyped Demo");
        stage.show();

    }

    private void handleKeyTypedEvent(KeyEvent event) {
        System.out.printf("Name: %s, Character: %s%n", event.getEventType().getName(), event.getCharacter());
    }
}
