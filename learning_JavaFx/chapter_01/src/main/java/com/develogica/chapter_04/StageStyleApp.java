package com.develogica.chapter_04;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.stage.StageStyle.*;

public class StageStyleApp extends Application {
    private Stage stage;
    private double dragOffsetX;
    private double dragOffsetY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        Label styleLabel = new Label("Stage Style");
        Button exitButton = new Button("Close");
        exitButton.setOnAction(event -> Platform.exit());

        VBox rootGroup = new VBox();
        rootGroup.getChildren().addAll(styleLabel, exitButton);

        var scene = new Scene(rootGroup, 200, 100);
        scene.setOnMousePressed(this::handleMousePressed);
        scene.setOnMouseDragged(this::handleMouseDragged);

        System.out.println(Platform.isSupported(ConditionalFeature.UNIFIED_WINDOW));

        stage.setScene(scene);

//        show(stage, styleLabel, DECORATED);
//        show(stage, styleLabel, UNDECORATED);
//        show(stage, styleLabel, TRANSPARENT);
        show(stage, styleLabel, UNIFIED);
//        show(stage, styleLabel, UTILITY);

        stage.setTitle("Stage Style - " + stage.getStyle().toString());
        stage.show();
    }


    private void handleMousePressed(MouseEvent event) {
        this.dragOffsetX = event.getScreenX() - stage.getX();
        this.dragOffsetY = event.getScreenY() - stage.getY();
    }

    private void handleMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - this.dragOffsetX);
        stage.setY(event.getScreenY() - this.dragOffsetY);
    }

    private void show(Stage stage, Label styleLabel, StageStyle style) {
        styleLabel.setText(style.toString());

        stage.initStyle(style);

        if (style == TRANSPARENT) {
            stage.getScene().setFill(Color.color(.2, .3, .5, .5));
            stage.getScene().getRoot().setStyle("-fx-background-color: transparent");
        } else if (style == UNIFIED) {
            stage.getScene().setFill(Color.TRANSPARENT);
        }
    }
}
