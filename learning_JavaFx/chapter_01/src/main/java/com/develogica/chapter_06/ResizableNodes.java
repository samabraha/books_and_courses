package com.develogica.chapter_06;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class ResizableNodes extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("A resizable button.");
        Rectangle rectangle = new Rectangle(100, 50);


        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(2);
        rectangle.setStroke(Color.BLACK);
        rectangle.arcHeightProperty().bind(rectangle.arcWidthProperty().divide(3));
        rectangle.setArcWidth(50);

        var root = new HBox();
        root.setSpacing(20);
        root.getChildren().addAll(button, rectangle);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Resizable Nodes");
        stage.show();

        System.out.println("button.isResizable() = " + button.isResizable());
        System.out.println("rectangle.isResizable() = " + rectangle.isResizable());
    }
}
