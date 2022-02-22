package com.develogica.chapter_06.bounds;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NodeBoundsApp extends Application {
    private static class VBoxUtil {
        public static VBox fromChildren(Node... children) {
            var vb = new VBox();
            vb.getChildren().addAll(children);
            return vb;
        }
    }

    final static int AXIS_LENGTH = 150;
    final static int ARROW_LENGTH = 5;

    final static int RECTANGLE_WIDTH = 50;
    final static int RECTANGLE_HEIGHT = 20;
    final static double RECTANGLE_OPACITY = 0.5;
    final static Color RECTANGLE_STROKE_COLOR = Color.PURPLE;
    final static String RECTANGLE_STROKE_COLOR_STR = "PURPLE";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
