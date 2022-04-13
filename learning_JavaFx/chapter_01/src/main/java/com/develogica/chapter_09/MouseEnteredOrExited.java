package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MouseEnteredOrExited extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var circle = new Circle(50, 50, 50);
        circle.setFill(Color.LEMONCHIFFON);

        var root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(circle);

        EventHandler<MouseEvent> handler = this::handle;

        root.addEventHandler(MouseEvent.MOUSE_ENTERED, handler);
        root.addEventHandler(MouseEvent.MOUSE_EXITED, handler);

        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, handler);
        circle.addEventHandler(MouseEvent.MOUSE_EXITED, handler);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mouse Entered or Exited Demo");
        stage.show();
    }

    private void handle(MouseEvent event) {
        System.out.printf("Type:%s Source:%s Target:%s%n",
                event.getEventType(),
                event.getSource().getClass().getSimpleName(),
                event.getTarget().getClass().getSimpleName());
    }
}
