package com.develogica.chapter_12.play2048;

import com.develogica.util.ColorUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Play2048 extends Application {
    @Override
    public void start(Stage stage) throws InterruptedException  {
        var moveUpButton = new Button("Move Up");
        var exitButton = new Button("Exit");
        var buttonBox = new VBox(moveUpButton, exitButton);

        Board board = new Board(10);

        var pane = new GridPane();
        pane.setPrefSize(400, 400);
        for (int i = 0; i < board.tiles.length; i++) {
            for (int j = 0; j < board.tiles[i].length; j++) {
                var tile = board.tiles[i][j];
                if (tile != null) {
                    var tileLabel = new Label(String.valueOf(tile.value));
                    tileLabel.setPrefSize(40, 40);

                    var colorStr = ColorUtil.getCssRgbFunctionString(tile.color);
                    tileLabel.setStyle("-fx-background-color: %s".formatted(colorStr));

                    GridPane.setConstraints(tileLabel, i, j);

                    pane.getChildren().add(tileLabel);
                }
            }
        }

        moveUpButton.setOnAction(event -> Board.moveUp(pane));

        var root = new BorderPane();
        root.setCenter(pane);
        root.setRight(buttonBox);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Play2048");
        stage.show();
    }
}

