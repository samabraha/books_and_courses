package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TilePaneTileAlignment extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var tileAlignmentCenter = createTilePane(Pos.CENTER);
        var tileAlignmentTopRight = createTilePane(Pos.TOP_LEFT);

        var root = new HBox(tileAlignmentCenter, tileAlignmentTopRight);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TilePane Tile-Alignment Demo");
        stage.show();
    }

    private TilePane createTilePane(Pos alignment) {
        List<String> names = List.of("Button", "Tiles", "Are", "Aligned", "At", alignment.toString());
        List<Button> buttons = new ArrayList<>();
        names.forEach(name -> {
            buttons.add(new Button(name));
        });

        var tilePane = new TilePane(5, 5, buttons.toArray(new Button[0]));
        tilePane.setAlignment(alignment);
        tilePane.setPrefColumns(3);
        tilePane.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: teal;
                """);
        return tilePane;
    }
}

