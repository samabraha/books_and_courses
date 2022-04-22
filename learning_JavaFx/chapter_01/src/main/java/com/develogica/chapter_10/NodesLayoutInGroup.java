package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class NodesLayoutInGroup extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var okButton = new Button("OK");
        var cancelButton = new Button("Cancel");

        okButton.setLayoutX(10);
        okButton.setLayoutY(10);

        var layoutBinding = okButton.layoutXProperty().add(okButton.widthProperty().add(10));

        cancelButton.layoutXProperty().bind(layoutBinding);
        cancelButton.layoutYProperty().bind(okButton.layoutYProperty());

        var root = new Group(okButton, cancelButton);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Nodes Layout in Group Demo");
        stage.show();
    }
}

