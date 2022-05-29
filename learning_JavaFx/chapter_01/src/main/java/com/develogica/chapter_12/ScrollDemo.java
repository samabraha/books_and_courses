package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        var addButton = new Button("ADD");

        var buttonsVBox = new VBox(addButton);
        addButton.setOnAction(event -> buttonsVBox.getChildren().add(new Button("B:" + (int)(Math.random() * 10000))));

        var root = new ScrollPane(buttonsVBox);

        var scene = new Scene(root, 200, 300);

        stage.setScene(scene);
        stage.setTitle("Scroll Demo");
        stage.show();
    }
}
