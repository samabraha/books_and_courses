package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckerBoard extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        var aButton = new Button("a");
        var bButton = new Button("b");
        var cButton = new Button("c");
        var exitButton = new Button("exit");

        var buttonsVBox = new VBox(10, aButton, bButton, cButton, exitButton);

        var boardWidth = 10;
        var boardHeight = 5;

        var board = new TilePane(5, 5);
        board.setStyle("-fx-background-color: white;");
        board.setAlignment(Pos.CENTER);
        board.setPrefColumns(boardWidth);
        board.setPrefRows(boardHeight);

        var color = Color.BLUE;
        for (int i = 1; i <= boardHeight; i++) {
            color = color.equals(Color.BLUE) ? Color.RED : Color.BLUE;
            for (int j = 1; j <= boardWidth; j++) {
                color = color.equals(Color.BLUE) ? Color.RED : Color.BLUE;
                var rect = new Rectangle(40, 40, color);

                board.getChildren().add(rect);
            }
        }

        var root = new BorderPane();

        root.setCenter(board);
        root.setRight(buttonsVBox);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Checkers Board Demo");
        stage.show();

        var width = board.getWidth();
        var height = board.getHeight();
        board.setPrefSize(width, height);
    }
}
