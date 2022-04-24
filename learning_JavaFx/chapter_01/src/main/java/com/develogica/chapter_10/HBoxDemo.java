package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HBoxDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var nameLabel = new Label("Name");
        var nameTextField = new TextField();

        var root = new HBox(10);

        var okButton = new Button("OK");
//        okButton.prefHeightProperty().bind(root.heightProperty().subtract(root.getSpacing()));
        okButton.maxHeight(Double.MAX_VALUE);

        var cancelButton = new Button("Cancel");
//        cancelButton.prefHeightProperty().bind(root.heightProperty().subtract(root.getSpacing()));
        cancelButton.maxHeight(Double.MAX_VALUE);

        var fillHeightChkBox = new CheckBox("Fill Height");
        fillHeightChkBox.setOnAction(event -> {
            root.setFillHeight(fillHeightChkBox.isSelected());
        });

        HBox.setHgrow(nameTextField, Priority.ALWAYS);

        root.getChildren().addAll(nameLabel, nameTextField, fillHeightChkBox, okButton, cancelButton);
        root.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: teal;
                """);
//        root.setAlignment(Pos.);

        var scene = new Scene(root);
        stage.setScene(scene);
//        stage.sizeToScene();
        stage.setTitle("HBox Demo");
        stage.show();
    }
}
