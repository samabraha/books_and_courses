package com.develogica.chapter_08;

import com.develogica.util.ResourceUtil;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BorderStyleDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var root = new HBox();

        var styleSheetUrl = ResourceUtil.getResourceURLString("css/lineStyles.css");

        var pane1 = new Pane();
        pane1.setPrefSize(200, 200);
        pane1.getStyleClass().add("my-style-1");

        var pane2 = new Pane();
        pane2.setPrefSize(200, 200);
        pane2.getStyleClass().add("my-style-2");

        var pane3 = new Pane();
        pane3.setPrefSize(200, 200);
        pane3.getStyleClass().add("my-style-3");

        var pane4 = new Pane();
        pane4.setPrefSize(200, 200);
        pane4.getStyleClass().add("my-style-4");


        root.getChildren().addAll(pane1, pane2, pane3, pane4);
        var scene = new Scene(root);
        scene.getStylesheets().addAll(styleSheetUrl);
        stage.setScene(scene);
        stage.setTitle("BorderStyle Demo");
        stage.show();
    }
}
