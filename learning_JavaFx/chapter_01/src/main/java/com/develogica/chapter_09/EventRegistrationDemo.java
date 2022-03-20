package com.develogica.chapter_09;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class EventRegistrationDemo extends Application {
    static int handledCount = 0;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var circle = new Circle(100, 100, 50);
        circle.setFill(Color.CORAL);

        EventHandler<MouseEvent> mouseEventFilter = event ->
                System.out.println("Mouse event filter has been called");

        EventHandler<MouseEvent> mouseEventHandler = event ->
        System.out.println("Mouse event handler has been called");

        circle.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEventFilter);
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventHandler);

        var shapesHBox = new HBox();
        shapesHBox.getChildren().add(circle);


        var nameLabel = new Label("Name");
        var nameTextField = new TextField();

        var okButton = new Button("OK");
        var exitButton = new Button("Exit");
        exitButton.setOnAction(event -> Platform.exit());

        var root = new VBox(10);
        root.getChildren().addAll(shapesHBox, nameLabel, nameTextField, okButton, exitButton);

        var scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Event-Registration Demo");
        stage.show();
    }
}
