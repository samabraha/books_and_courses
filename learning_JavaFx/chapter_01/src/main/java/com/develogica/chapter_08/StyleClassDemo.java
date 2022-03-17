package com.develogica.chapter_08;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StyleClassDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        var nameLbl = new Label("Name:");
        var nameTf = new TextField("");

        var okButton = new Button("OK");

        var exitButton = new Button("Exit");
        exitButton.setId("exitButton");
        exitButton.setOnAction(event -> Platform.exit());

        var root = new HBox();
        root.getChildren().addAll(nameLbl, nameTf, okButton, exitButton);
        root.getStyleClass().add("hbox");

        var scene = new Scene(root);

        scene.getStylesheets().add("file:resources/css/styleClass.css");

        stage.setScene(scene);
        stage.setTitle("Style Class Demo");
        stage.show();

    }
}
