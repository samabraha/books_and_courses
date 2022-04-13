package com.develogica.chapter_09.keys;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class KeyPressedReleased extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var nameLabel = new Label("Name:");

        var nameTField = new TextField();
        nameTField.setOnKeyPressed(this::handleKeyEvent);
        nameTField.setOnKeyReleased(this::handleKeyEvent);

        var root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(nameLabel, nameTField);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Key-Pressed & Key-Released Demo");
        stage.show();
    }

    private void handleKeyEvent(KeyEvent event) {
        var type = event.getEventType().getName();
        var keyCode = event.getCode();
        System.out.printf("Type: %s, Code: %s, Text: %s%n", type, keyCode, event.getText());
        if (event.getEventType() == KeyEvent.KEY_PRESSED
                && event.getCode() == KeyCode.F1) {
            displayHelp();
            event.consume();
        }
    }

    public void displayHelp() {
        var helpText = new Text("Please enter a name.");
        var root = new HBox();
        root.setStyle("-fx-background-color: yellow;");
        root.getChildren().add(helpText);

        var scene = new Scene(root, 200, 100);
        var helpStage = new Stage();
        helpStage.setScene(scene);
        helpStage.setTitle("Help");
        helpStage.show();

    }
}
