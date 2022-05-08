package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonDemo extends Application {
    Label msgLabel = new Label("Press Enter or Esc key to see the message.");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        var newButton = new Button("_New");
        newButton.setStyle("""
                -fx-font-family: 'Helvetica', Arial, sans-serif;
                """);
        newButton.setTooltip(new Tooltip());
        newButton.setOnAction(event -> createDocument());

        var saveButton = new Button("_Save");
        saveButton.setDefaultButton(true);
        saveButton.setOnAction(event -> saveDocument());

        var cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(event -> cancel());

        var buttonBox = new HBox(15, newButton, saveButton, cancelButton);

        var textArea = new TextArea();

        var root = new VBox(10, msgLabel, buttonBox, textArea);
        root.setStyle("""
                -fx-padding: 10;
                """);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Button Demo");
        stage.show();
    }

    private void cancel() {
        msgLabel.setText("Cancelling...");
        Platform.exit();
    }

    private void saveDocument() {
        msgLabel.setText("Saving document...");
    }

    private void createDocument() {
        msgLabel.setText("Creating new document...");
    }
}
