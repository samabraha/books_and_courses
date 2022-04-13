package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MouseLocation extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var circle = new Circle(50, 50, 50, Color.CORAL);
        var rectangle = new Rectangle(100, 100, Color.TAN);
        var exitButton = new Button("Exit");

        exitButton.setOnAction(event -> Platform.exit());

        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);

        root.getChildren().addAll(circle, rectangle, exitButton);

        var scene = new Scene(root);

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleEvent);
        stage.setScene(scene);
        stage.setTitle("Mouse Location Demo");
        stage.show();
    }

    private void handleEvent(MouseEvent event) {
        String source = event.getSource().getClass().getSimpleName();
        String target = event.getTarget().getClass().getSimpleName();

        double x = event.getX();
        double y = event.getY();

        double sceneX = event.getSceneX();
        double sceneY = event.getSceneY();

        double screenX = event.getScreenX();
        double screenY = event.getScreenY();

        System.out.printf("x:%4.0f y:%4.0f scene[x:%-4.0f %4.0f:y] " +
                        "screen[x:%4.0f y:%4.0f] \tS:%s \tT:%s%n",
                x, y, sceneX, sceneY, screenX, screenY, source, target);
    }
}
