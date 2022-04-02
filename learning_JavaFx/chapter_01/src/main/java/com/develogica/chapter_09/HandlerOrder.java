package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class HandlerOrder extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        var circle = new Circle(50, 50, 50);
        circle.setFill(Color.CORAL);


        var root = new HBox();
        root.getChildren().add(circle);
        var scene = new Scene(root);

        circle.addEventHandler(MouseEvent.ANY, event -> handleAnyMouseEvent(event) );
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> handleMouseClicked("addEventHandler()", event) );
        circle.setOnMouseClicked(event -> handleMouseClicked("setOnMouseClicked()", event));


        stage.setScene(scene);
        stage.setTitle("Handler-Order Demo");
        stage.show();
    }

    private void handleMouseClicked(String method, MouseEvent event) {
        System.out.println(method + ": MOUSE_CLICKED handler detected a mouse click.");
    }

    private void handleAnyMouseEvent(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            System.out.println("MouseEvent.ANY handler detcted a mouse click");
        }
    }
}
