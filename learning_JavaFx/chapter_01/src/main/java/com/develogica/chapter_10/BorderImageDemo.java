package com.develogica.chapter_10;

import com.develogica.util.ResourceUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BorderImageDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var url = ResourceUtil.getResourceURLString("pictures/border_with_circles.jpg");

        var pane1 = getCSSStyledPane(url);
        var pane2 = getObjectStyledPane(url);

        pane1.setLayoutX(20);
        pane1.setLayoutY(20);

        pane2.layoutXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty().add(20)));
        pane2.layoutYProperty().bind(pane1.layoutYProperty());

        var root = new Pane(pane1, pane2);
        root.setPrefSize(400, 200);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Border-Image Demo");
        stage.show();
    }

    private Pane getCSSStyledPane(String imageUrl) {
        var pane = new Pane();
        pane.setPrefSize(200, 150);
        pane.setStyle("""
                -fx-border-image-source: url('%s');
                -fx-border-image-repeat: no-repeat;
                -fx-border-image-slice: 9;
                -fx-border-image-width: 20;
                -fx-border-image-insets: 10;
                -fx-border-color: black;
                -fx-border-width: 3;
                -fx-border-style: dashed inside;
                """.formatted(imageUrl));
        return pane;
    }

    private Pane getObjectStyledPane(String imageUrl) {
        var pane = new Pane();
        pane.setPrefSize(200, 150);
        pane.setBackground(Background.EMPTY);

        var regionWidths = new BorderWidths(20);
        var sliceWidths = new BorderWidths(20);
        var filled = false;

        var repeatX = BorderRepeat.STRETCH;
        var repeatY = BorderRepeat.STRETCH;

        var borderImage = new BorderImage(new Image(imageUrl), regionWidths,
                new Insets(10), sliceWidths, filled, repeatX, repeatY);

        List<Double> dashedArray = new ArrayList<>();
        dashedArray.add(2.0);
        dashedArray.add(1.4);

        var blackStrokeStyle = new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.MITER,
                StrokeLineCap.BUTT, 10, 0, dashedArray);

        var borderStroke = new BorderStroke(Color.BLACK, blackStrokeStyle,
                CornerRadii.EMPTY, new BorderWidths(1), new Insets(0));

        var strokes = new BorderStroke[] { borderStroke };
        var images = new BorderImage[] { borderImage };
        var border = new Border(strokes, images);

        pane.setBorder(border);
        return pane;
    }
}

