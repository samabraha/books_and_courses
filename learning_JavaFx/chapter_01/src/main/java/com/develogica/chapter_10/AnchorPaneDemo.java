package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){

        var button1 = new Button("Try this button first!");
        var button2 = new Button("Then try this button!");

        var root = new AnchorPane(button1, button2);

        AnchorPane.setTopAnchor(button1, 20.0);
        AnchorPane.setLeftAnchor(button1, 10.0);
        AnchorPane.setRightAnchor(button1, 10.0);

        AnchorPane.setLeftAnchor(button2, 10d);
        AnchorPane.setRightAnchor(button2, 10d);
        AnchorPane.setBottomAnchor(button2, 20d);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("AnchorPane Demo");
        stage.show();
    }
}
