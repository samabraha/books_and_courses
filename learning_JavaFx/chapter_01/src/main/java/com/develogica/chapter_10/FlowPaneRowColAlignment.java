package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.geometry.Orientation.HORIZONTAL;
import static javafx.geometry.Orientation.VERTICAL;

public class FlowPaneRowColAlignment extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var flowPane1 = createFlowPane(HORIZONTAL, VPos.TOP, HPos.LEFT);
        var flowPane2 = createFlowPane(HORIZONTAL, VPos.CENTER, HPos.LEFT);
        var flowPane3 = createFlowPane(VERTICAL, VPos.CENTER, HPos.RIGHT);

        var root = new HBox(flowPane1, flowPane2, flowPane3);
        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FlowPane Row-Col Alignment Demo");
        stage.show();
    }

    private FlowPane createFlowPane(Orientation orientation, VPos rowAlign, HPos colAlign) {
        var text = new Text();
        if (orientation == HORIZONTAL) {
            text.setText(rowAlign.toString());
        } else {
            text.setText(colAlign.toString());
        }

        var textArea = new TextArea(orientation.toString());
        textArea.setPrefColumnCount(5);
        textArea.setPrefRowCount(3);

        var flowPane = new FlowPane(orientation, 5, 5);
        flowPane.setRowValignment(rowAlign);
        flowPane.setColumnHalignment(colAlign);
        flowPane.setPrefSize(175, 130);
        flowPane.getChildren().addAll(text, textArea);
        flowPane.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: blue;
                """);
        return flowPane;
    }
}

