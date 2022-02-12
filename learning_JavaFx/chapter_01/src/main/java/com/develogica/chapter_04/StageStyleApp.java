package com.develogica.chapter_04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.stage.StageStyle.*;

public class StageStyleApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label styleLabel = new Label("Stage Style");
        Button exitButton = new Button("Close");
        exitButton.setOnAction(event -> Platform.exit());

        VBox rootGroup = new VBox();
        rootGroup.getChildren().addAll(styleLabel, exitButton);

        var scene = new Scene(rootGroup, 200, 100);
        stage.setScene(scene);

//        show(stage, styleLabel, DECORATED);
//        show(stage, styleLabel, UNDECORATED);
//        show(stage, styleLabel, TRANSPARENT);
//        show(stage, styleLabel, UNIFIED);
//        show(stage, styleLabel, UTILITY);

        stage.setTitle("Stage Style - " + stage.getStyle().toString());
        stage.show();
    }

    private void show(Stage stage, Label styleLabel, StageStyle style) {
        styleLabel.setText(style.toString());

        stage.initStyle(style);

        if (style == TRANSPARENT) {
            stage.getScene().setFill(null);
            stage.getScene().getRoot().setStyle("-fx-background-color: transparent");
        } else if (style == UNIFIED) {
            stage.getScene().setFill(Color.TRANSPARENT);

        }
    }
}
