package com.develogica.chapter_04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ShowAndWaitDemo extends Application {
    protected static int counter = 0;
    protected Stage lastOpenStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        var root = new VBox();
        var openButton = new Button("Open");
        openButton.setOnAction(event -> open(++counter));

        var exitButton = new Button("Exit");
        exitButton.setOnAction(event -> Platform.exit());

        root.getChildren().addAll(openButton, exitButton);
        var scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("ShowAndWait App");
        stage.show();

        lastOpenStage = stage;
    }

    private void open(int stageNumber) {
        Stage stage = new Stage();
        stage.setTitle("Stage #" + stageNumber);

        var openButton = new Button("Open");
        openButton.setOnAction(event -> open(++counter));

        var helloButton = new Button("Say Hello");
        helloButton.setOnAction(event -> System.out.println("Hello for stage #" + stageNumber));

        var root = new VBox();
        root.getChildren().addAll(openButton, helloButton);

        var scene = new Scene(root, 200, 200);
        stage.setScene(scene);

        stage.setX(lastOpenStage.getX() + 50);
        stage.setY(lastOpenStage.getY() + 40);

        this.lastOpenStage = stage;

        System.out.println("Before stage.showAndWait(), stage #" + stageNumber);
        stage.showAndWait();
        System.out.println("After stage.showAndWait(), stage #" + stageNumber);
    }
}
