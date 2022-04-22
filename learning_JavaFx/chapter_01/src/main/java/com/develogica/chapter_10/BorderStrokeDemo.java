package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BorderStrokeDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var pane1 = getCSSStyledPane();
        var pane2 = getObjectStyledPane();

        pane1.setLayoutX(20);
        pane1.setLayoutY(20);

        pane2.layoutXProperty().bind(pane1.layoutXProperty()
                .add(pane1.widthProperty()).add(40));
        pane2.layoutYProperty().bind(pane1.layoutYProperty());

        var root = new Pane(pane1, pane2);
        root.setPrefSize(300, 120);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Border-Stroke Demo");
        stage.show();
        stage.sizeToScene();

        printBorderDetails(pane1.getBorder(), pane2.getBorder());
    }

    private Pane getCSSStyledPane() {
        var pane = new Pane();
        pane.setPrefSize(100, 50);
        pane.setStyle("""
                -fx-padding: 10;
                -fx-border-color: red, green, blue;
                -fx-border-style: solid inside, solid outside, dashed centered;
                -fx-border-width: 10, 8, 1;
                -fx-border-insets: 12, -10, 0;
                -fx-border-radius: 0, 0, 0;
                """);
        return pane;
    }

    private Pane getObjectStyledPane() {
        var pane = new Pane();
        pane.setPrefSize(100, 50);
        pane.setBackground(Background.EMPTY);
        pane.setPadding(new Insets(10));

        var redStroke = new BorderStroke(
                Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                new BorderWidths(10), new Insets(12)
        );

        var greenStrokeStyle = new BorderStrokeStyle(StrokeType.OUTSIDE, StrokeLineJoin.MITER,
                StrokeLineCap.BUTT, 10, 0, null
        );

        var greenStroke = new BorderStroke(
                Color.GREEN, greenStrokeStyle, CornerRadii.EMPTY,
                new BorderWidths(8), new Insets(-10));

        List<Double> dashArray = new ArrayList<>();
        dashArray.add(2.0);
        dashArray.add(1.4);

        var blueStrokeStyle = new BorderStrokeStyle(
                StrokeType.CENTERED, StrokeLineJoin.MITER,
                StrokeLineCap.BUTT, 10, 0, dashArray);

        var blueStroke = new BorderStroke(
                Color.BLUE, blueStrokeStyle, CornerRadii.EMPTY,
                new BorderWidths(1), new Insets(0));

        var border = new Border(redStroke, greenStroke, blueStroke);
        pane.setBorder(border);
        return pane;
    }

    private void printBorderDetails(Border cssBorder, Border objectBorder) {
        System.out.printf("cssBorder getInsets: %s%n", cssBorder.getInsets());
        System.out.printf("cssBorder getOutsets: %s%n", cssBorder.getOutsets());
        System.out.printf("objectBorder getInsets: %s%n", objectBorder.getInsets());
        System.out.printf("objectBorder getOutsets: %s%n", objectBorder.getOutsets());

        var message = cssBorder.equals(objectBorder) ? "" : " not";
        System.out.printf("Borders are%s equal.%n", message);
    }
}
