package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GridPaneDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var buttonsVBox = createButtons();
        var gridPane = createGridPane();
        var root = new HBox(gridPane, buttonsVBox);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("GridPane Demo");
        stage.show();
    }

    private GridPane createGridPane() {
        var label1 = new Rectangle(100, 50);
        label1.setFill(Color.hsb(0, .8, 1));
        var label2 = new Rectangle(50, 50);
        label2.setFill(Color.hsb(75, .8, 1));
        var label3 = new Rectangle(50, 50);
        label3.setFill(Color.hsb(150, .8, 1));
        var label4 = new Rectangle(50, 50);
        label4.setFill(Color.hsb(225, .8, 1));
        var label5 = new Rectangle(50, 200);
        label5.setFill(Color.hsb(300, .8, 1));

        var gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        gridPane.add(label1, 0, 0, 3, 1);
        gridPane.add(label2, 3, 0, 1, 1);
        gridPane.add(label3,2, 3, 1, 1);
        gridPane.add(label4, 1, 1, 1, 3);
        gridPane.add(label5, 0, 3, 1, 3);

        return gridPane;
    }

    private VBox createButtons() {
        var createButton = new Button("Create");
        var modifyButton = new Button("Modify");
        var okButton = new Button("OK");
        var cancelButton = new Button("Cancel");

        var buttonsVBox = new VBox(10, createButton, modifyButton, okButton, cancelButton);
        buttonsVBox.getChildren().forEach(node -> {
            if (node instanceof Button button) {
                button.setMaxWidth(Double.MAX_VALUE);
            }
        } );

        buttonsVBox.setPadding(new Insets(10));
        buttonsVBox.setAlignment(Pos.TOP_CENTER);
        return buttonsVBox;
    }
}
