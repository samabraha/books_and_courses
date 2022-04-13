package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PickOnBoundsDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        var rectangle = new Rectangle(100, 100, Color.RED);
        rectangle.setOnMouseClicked(event -> System.out.println("Rectangle"));

        var circle = new Circle(50, 50, 50, Color.BEIGE);
        circle.setOnMouseClicked(event -> System.out.println("Circle"));

        var group = new Group();
        group.getChildren().addAll(rectangle, circle);

        var pickOnBoundsChBox = new CheckBox("Pick on Bounds");
        pickOnBoundsChBox.setOnAction( event -> circle.setPickOnBounds(pickOnBoundsChBox.isSelected()));

        var mouseTransparentChBox = new CheckBox("Mouse Transparent");
        mouseTransparentChBox.setOnAction( event -> circle.setMouseTransparent(pickOnBoundsChBox.isSelected()));

        var exitButton = new Button("EXIT");
        exitButton.setOnAction(event -> Platform.exit());

        var optionsVBox = new VBox();
        optionsVBox.getChildren().addAll(pickOnBoundsChBox, mouseTransparentChBox, exitButton);

        var root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(group, optionsVBox);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Pick on Bounds Demo");
        stage.show();
    }
}
