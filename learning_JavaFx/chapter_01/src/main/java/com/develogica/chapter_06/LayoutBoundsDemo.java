package com.develogica.chapter_06;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutBoundsDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var button1 = new Button("Close");
        button1.setEffect(new DropShadow());

        var button2 = new Button("Close");

        var button3 = new Button("Close");
        button3.setEffect(new DropShadow());
        button3.setRotate(30);

        var button4 = new Button("Close");
        button4.setOnAction(event -> Platform.exit());

        var root = new VBox();
        root.getChildren().addAll(button1, button2, button3, button4);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Layout Bounds Demo");
        stage.show();

        System.out.println("button-1 " + button1.getLayoutBounds());
        System.out.println("button-2 " + button2.getLayoutBounds());
        System.out.println("button-3 " + button3.getLayoutBounds());
        System.out.println("button-4 " + button4.getLayoutBounds());
    }
}
