package com.develogica.chapter_06;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NodeSizes extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var helloButton = new Button("Hello, JavaFX Button");
        var root = new HBox();
        root.getChildren().addAll(helloButton);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sizes of a Node");
        stage.show();

        System.out.println("Before changing button properties");
        printSizes(helloButton);
        helloButton.setWrapText(true);
        helloButton.setPrefWidth(80);
        stage.sizeToScene();
        System.out.println("After changing button properties");
        printSizes(helloButton);
    }

    private void printSizes(Button btn) {
        System.out.println("btn.getContentBias() = " + btn.getContentBias());
        System.out.print("btn.getPrefWidth() = " + btn.getPrefWidth());
        System.out.println(" | btn.getPrefHeight() = " + btn.getPrefHeight());
        System.out.print("btn.getMinWidth() = " + btn.getMinWidth());
        System.out.println(" | btn.getMinHeight() = " + btn.getMinHeight());
        System.out.print("btn.getMaxWidth() = " + btn.getMaxWidth());
        System.out.println(" | btn.getMaxHeight() = " + btn.getMaxHeight());

        double prefWidth = btn.prefWidth(-1);
        System.out.println("btn.prefHeight(-1) = " + btn.prefHeight(prefWidth));

        double minWidth = btn.minWidth(-1);
        double maxWidth = btn.maxWidth(-1);
        System.out.print("btn.minHeight(minWidth) = " + btn.minHeight(minWidth));
        System.out.println(" | btn.maxHeight(maxWidth) = " + btn.maxHeight(maxWidth));

        System.out.print("btn.getWidth() = " + btn.getWidth());
        System.out.println(" | btn.getHeight() = " + btn.getHeight());

    }
}
