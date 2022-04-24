package com.develogica.chapter_10;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxFillWidthDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var root = new VBox(10);

        root.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 10 10 0 0;
                -fx-border-color: teal;
                """);

        var titleLabel = new Label("VBox FillWidth Demo");
//        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setStyle("-fx-background-color: lemon;");
        var createButton = new Button("Create");
        var modifyButton = new Button("Modify");
        var deleteButton = new Button("Delete");
        var okButton = new Button("OK");
        var cancelButton = new Button("Cancel");

//        createButton.setMaxWidth(Double.MAX_VALUE);
//        modifyButton.setMaxWidth(Double.MAX_VALUE);
//        deleteButton.setMaxWidth(Double.MAX_VALUE);
//        okButton.setMaxWidth(Double.MAX_VALUE);
//        cancelButton.setMaxWidth(Double.MAX_VALUE);

        root.getChildren().addAll(titleLabel, createButton, modifyButton, deleteButton, okButton, cancelButton);

        root.getChildren().forEach(node -> ((Region) node).setMaxWidth(Double.MAX_VALUE));

        root.getChildren().forEach(node
                -> node.setOnMouseClicked(event
                -> System.out.println("Clicked " + ((Labeled) event.getSource()).getText())));

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("VBox FillWidth Demo");
        stage.show();
    }
}

