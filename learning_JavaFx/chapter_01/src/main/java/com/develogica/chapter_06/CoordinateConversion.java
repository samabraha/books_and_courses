package com.develogica.chapter_06;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CoordinateConversion extends Application {
    private Circle marker;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        var firstName = new TextField();
        var lastName = new TextField();
        var salary = new TextField();

        marker = new Circle(5);
        marker.setManaged(false);
        marker.setFill(Color.RED);
        marker.setMouseTransparent(true);

        var hBox1 = new HBox();
        var hBox2 = new HBox();
        var hBox3 = new HBox();

        hBox1.getChildren().addAll(new Label("Fist Name"), firstName);
        hBox2.getChildren().addAll(new Label("Last Name"), lastName);
        hBox3.getChildren().addAll(new Label("Salary"), salary);

        var root = new VBox();
        root.getChildren().addAll(hBox1, hBox2, hBox3, marker);

        var scene = new Scene(root);

        scene.focusOwnerProperty().addListener((prop, oldNode, newNode) -> placeMarker(newNode));

        stage.setScene(scene);
        stage.setTitle("Coordinate Conversion");
        stage.show();
    }

    private void placeMarker(Node newNode) {
        double nodeMinX = newNode.getLayoutBounds().getMinX();
        double nodeMinY = newNode.getLayoutBounds().getMinY();

        Point2D nodeInScene = newNode.localToScene(nodeMinX, nodeMinY);
        Point2D nodeInMarkerLocal = marker.sceneToLocal(nodeInScene);
        Point2D nodeInMarkerParent = newNode.localToParent(nodeInMarkerLocal);

        marker.relocate(nodeInMarkerParent.getX() + marker.getLayoutBounds().getMinX(),
                nodeInMarkerParent.getY() + marker.getLayoutBounds().getMinY());

    }
}
;