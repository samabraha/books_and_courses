package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;

public class GroupEffect extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var okButton = new Button("OK");
        var cancelButton = new Button("Cancel");

        okButton.setLayoutX(10);
        okButton.setLayoutY(10);
        cancelButton.setLayoutX(80);
        cancelButton.setLayoutY(10);

        var root = new Group(okButton, cancelButton);

        root.setEffect(new DropShadow());
        root.setRotate(10);
        root.setOpacity(0.80);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Group Effect");
        stage.show();
    }
}
