package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;


public class CaptureBubblingOrder extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var circle = new Circle(50, 50, 50);
        circle.setFill(Color.CORAL);

        var rectangle = new Rectangle(100, 100);
        rectangle.setFill(Color.TAN);

        var root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(circle, rectangle);

        var scene = new Scene(root);

        EventHandler<MouseEvent> filter = event -> handleEvent("Capture", event);
        EventHandler<MouseEvent> handler = event -> handleEvent("Bubbling", event);

        stage.addEventFilter(MOUSE_CLICKED, filter);
        scene.addEventFilter(MOUSE_CLICKED, filter);
        root.addEventFilter(MOUSE_CLICKED, filter);
        circle.addEventFilter(MOUSE_CLICKED, filter);

        stage.addEventHandler(MOUSE_CLICKED, handler);
        scene.addEventHandler(MOUSE_CLICKED, handler);
        root.addEventHandler(MOUSE_CLICKED, handler);
        circle.addEventHandler(MOUSE_CLICKED, handler);


        stage.setScene(scene);
        stage.setTitle("Capture and Bubbling Order Demo");
        stage.show();
    }

    private void handleEvent(String phase, MouseEvent event) {
        var type = event.getEventType().getName();
        var source = event.getSource().getClass().getSimpleName();
        var target = event.getTarget().getClass().getSimpleName();

        double x = event.getX();
        double y = event.getY();

        System.out.printf("%s Type: %s, Source: %s, Target: %s, [x:%.0f, y:%.0f] %n",
                phase, type, source, target, x, y);
    }
}
