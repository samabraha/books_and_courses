package com.develogica.chapter_06;


import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SlidingLeftNodeDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var button1 = new Button("B 1");
        var button2 = new Button("B 2");
        var button3 = new Button("B 3");
        var visibleButton = new Button("Make Visible");

        visibleButton.setOnAction(event -> button2.setVisible(!button2.isVisible()));

        visibleButton.textProperty().bind(new When(button2.visibleProperty())
        .then("Make Invisible")
        .otherwise("Make Visible"));

        button2.managedProperty().bind(button2.visibleProperty());
        
        var root = new HBox();
        root.getChildren().addAll(visibleButton, button1, button2, button3);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sliding Toggle Demo");
        stage.show();
        
    }
}