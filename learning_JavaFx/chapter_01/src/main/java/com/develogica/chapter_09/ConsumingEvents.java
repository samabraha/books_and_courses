package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class ConsumingEvents extends Application {
    private final CheckBox consumeCheckbox = new CheckBox("Consume mouse click at Circle");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var circle = new Circle(50, 50, 50, Color.CORAL);
        var rectangle = new Rectangle(100, 100, Color.TAN);

        var root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(circle, rectangle, consumeCheckbox);
        var scene = new Scene(root);

        EventHandler<MouseEvent> handler = this::handleEvent;
        EventHandler<MouseEvent> circleMEHandler = this::handleEventForCircle;

        stage.addEventHandler(MOUSE_CLICKED, handler);
        scene.addEventHandler(MOUSE_CLICKED, handler);
        root.addEventHandler(MOUSE_CLICKED, handler);
        circle.addEventHandler(MOUSE_CLICKED, circleMEHandler);

        stage.setScene(scene);
        stage.setTitle("Consuming Events Demo");
        stage.show();
    }

    private void handleEventForCircle(MouseEvent event) {
        print(event);
    }

    private void handleEvent(MouseEvent event) {
        print(event);
        if (consumeCheckbox.isSelected()) {
            event.consume();
        }
    }

    private void print(MouseEvent event) {
        String type = event.getEventType().getName();
        var target = event.getTarget().getClass().getSimpleName();
        var source = event.getSource().getClass().getSimpleName();

        var x = event.getX();
        var y = event.getY();
        System.out.printf("Type: %s, Source: %s, Target: %s, [x:%.0f, y:%.0f]%n",
                type, source, target, x, y);
    }
}
