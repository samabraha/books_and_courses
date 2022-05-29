package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplitPaneDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        var side1Pane = (FlowPane) GUIFactory.getButtons(null, "Good", "Bad", "Evil", "None");
        var side2Pane = (FlowPane) GUIFactory.getButtons(null, "Good", "Bad", "Evil", "None");

        var splitPane = new SplitPane(side2Pane, side1Pane);

        var root = new VBox(new Label("All Below"), splitPane);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SplitPane Demo");
        stage.show();
    }
}
