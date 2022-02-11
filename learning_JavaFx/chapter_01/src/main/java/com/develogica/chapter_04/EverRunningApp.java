package com.develogica.chapter_04;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EverRunningApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group(new Button("Hello"));
        var scene = new Scene(root, 300, 100);
        stage.setScene(scene);
//        stage.setWidth(300);
//        stage.setHeight(200);
        stage.setTitle("Blank Stage");
        stage.setWidth(400);
        stage.setHeight(150);
        stage.centerOnScreen();
        stage.show();
//        Thread.sleep(5000);
//        stage.close();
    }
}
