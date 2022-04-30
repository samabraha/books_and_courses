package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class GridPaneForm extends Application {
    public static void main (String... args) {
        launch(args);
    }

    public void start(Stage stage) {
        var nameLabel = new Label("Name");
        var nameTextField = new TextField();

        var occupationLabel = new Label("Occupation");
        var occupationTextField = new TextField();

        var descrLabel = new Label("Description");
        var descrTextArea = new TextArea();
        descrTextArea.setPrefColumnCount(20);
        descrTextArea.setPrefRowCount(5);

        var okButton = new Button("OK");
        okButton.setMaxWidth(Double.MAX_VALUE);
        okButton.setOnAction(event -> Platform.exit());

        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> Platform.exit());

        var statusBarLabel = new Label("Status: ready");
        statusBarLabel.setMaxWidth(Double.MAX_VALUE);
        statusBarLabel.setStyle("""
                -fx-background-color: rgb(205, 215, 230);
                -fx-font-size: 7pt;
                -fx-padding: 5;
                """);

        var root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setStyle("""
                -fx-background-color: rgb(195, 205, 220);
                -fx-padding: 5;
                """);

//        root.setGridLinesVisible(true);

        root.add(nameLabel, 0, 0, 1,1);
        root.add(nameTextField, 1, 0, 1,1);
        root.add(occupationLabel, 0, 1, 1, 1);
        root.add(occupationTextField, 1, 1, 1, 1);
        root.add(descrLabel, 0, 2, 3,1);
        root.add(descrTextArea, 0, 3, 2,1);
        root.add(okButton, 2, 0, 1,1);
        root.add(cancelButton, 2, 1, 1,1);
        root.add(statusBarLabel, 0, 4, GridPane.REMAINING,1);

        GridPane.setHgrow(nameTextField, Priority.ALWAYS);
        GridPane.setVgrow(descrTextArea, Priority.ALWAYS);

//        nameLabel.setVisible(false);
//        nameTextField.setVisible(false);
//        occupationLabel.setVisible(false);
//        occupationTextField.setVisible(false);
//        descrLabel.setVisible(false);
//        descrTextArea.setVisible(false);
//        okButton.setVisible(false);
//        cancelButton.setVisible(false);
//        statusBarLabel.setVisible(false);

//        root.getChildren().forEach(child -> child.setVisible(false));

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("GridPane Form Demo");
        stage.show();
    }
}

