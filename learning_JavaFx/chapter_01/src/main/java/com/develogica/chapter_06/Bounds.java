package com.develogica.chapter_06;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.awt.*;

public class Bounds extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var layoutBoundsDemoButton = new Button("LayoutBounds Demo");
        layoutBoundsDemoButton.setOnAction(event -> LayoutBoundsDemo.showBounds());

        var boundsInLocalDemoButton = new Button("BoundsInLocal Demo");
        boundsInLocalDemoButton.setOnAction(event -> BoundsInLocalDemo.showBounds());

        var exitButton = new Button("Exit");
        exitButton.setOnAction(event -> Platform.exit());

        var root = new VBox();
        root.getChildren().add(layoutBoundsDemoButton);
        root.getChildren().add(boundsInLocalDemoButton);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Bounds Demo");
        stage.show();
    }
}

class LayoutBoundsDemo {
    public static void showBounds() {
        var button1 = new Button("Close");
        button1.setEffect(new DropShadow());

        var button2 = new Button("Close");

        var button3 = new Button("Close");
        button3.setEffect(new DropShadow());
        button3.setRotate(30);

        var button4 = new Button("Close");


        var root = new VBox();
        root.getChildren().addAll(new Group(button1), button2, new Group(button3), button4);
        root.setStyle("-fx-background-color: transparent;");
        var scene = new Scene(root);
        var stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        button4.setOnAction(event -> stage.close());

        stage.setScene(scene);
        stage.setTitle("Layout Bounds Demo");
        stage.show();

        System.out.println("button-1 " + button1.getLayoutBounds());
        System.out.println("button-2 " + button2.getLayoutBounds());
        System.out.println("button-3 " + button3.getLayoutBounds());
        System.out.println("button-4 " + button4.getLayoutBounds());
    }
}

class BoundsInLocalDemo {
    public static void showBounds() {
        var button1 = new Button("Close");
        button1.setEffect(new DropShadow());

        var root = new VBox();
        root.getChildren().add(button1);

        var scene = new Scene(root);

        var stage = new Stage();
        button1.setOnAction(event -> stage.close());

        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.setTitle("BoundsInLocal Demo");
        stage.show();

        System.out.println("button1.getLayoutBounds() = " + button1.getLayoutBounds());
        System.out.println("button1.getBoundsInLocal() = " + button1.getBoundsInLocal());
    }
}