package com.develogica.chapter_12.play2048;

import com.develogica.util.ColorUtil;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Tile[][] tiles;

    public Board(int size) {
        tiles = new Tile[size][size];

        var col = ThreadLocalRandom.current().nextInt(size);
        var row = ThreadLocalRandom.current().nextInt(size);
        System.out.printf("col:%d, row:%d%n", col, row);
        var tile = new Tile(ColorUtil.getRandomColorForBrightness(75), 2);
        tiles[col][row] = tile;
    }

    public static void moveUp(GridPane board) {
        var tiles = board.getChildren();
        for (var tile : tiles) {
            int col = GridPane.getColumnIndex(tile);
            int row = GridPane.getRowIndex(tile);
            System.out.printf("GP col:%d, row:%d%n", col, row);

            if (row > 0) {
                var value = Integer.parseInt(((Label) tile).getText());
                var newValue = value * 2;
                ((Label) tile).setText(String.valueOf(newValue));
                GridPane.setRowIndex(tile, row - 1);
            }
        }
    }
}
