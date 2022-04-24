package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FlowPaneAlignmentDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        var flowPane1 = createFlowPane(4);
        var flowPane2 = createFlowPane(5);
        var flowPane3 = createFlowPane(6);
        var flowPane4 = createFlowPane(7);

        var root = new HBox(flowPane1, flowPane2, flowPane3, flowPane4);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FlowPane Alignment Demo");
        stage.show();
    }

    /** Creates, styles and returns a new FlowPane buttons number of buttons. */
    private FlowPane createFlowPane(int buttons) {
        var flowPane = new FlowPane(5, 5);
        flowPane.setPrefSize(200, 100);
        flowPane.setAlignment(Pos.CENTER);

        if (buttons < 0) {
            buttons = -buttons;
        }

        for (int i = 1; i <= buttons; i++) {
            var button = new Button("Button " + i);
            button.setFocusTraversable(false);
            flowPane.getChildren().add(button);
        }

        flowPane.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: blue green;
                """);

        return flowPane;
    }
}

