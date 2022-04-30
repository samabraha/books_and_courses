package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GridPaneColRowConstraintsDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var root = new GridPane();
        root.setStyle("""
                -fx-padding: 10;
                """);
        root.setGridLinesVisible(true);

        var buttonStyle = """
                .button {-fx-background-color:\s
                    #000000,
                    linear-gradient(#7ebcea, #2f4b8f),
                    linear-gradient(#426ab7, #263e75),
                    linear-gradient(#395cab, #223768);
                -fx-background-insets: 0,1,2,3;
                -fx-background-radius: 3,2,2,2;
                -fx-padding: 12 30 12 30;
                -fx-text-fill: white;
                -fx-font-size: 12px;
                }
                
                .button:hover {
                -fx-background-color:\s
                    #000000,
                    linear-gradient(#7ebcff, #2f4b8f),
                    linear-gradient(#426aff, #263e75),
                    linear-gradient(#395cab, #223768);
                -fx-background-insets: 0,1,2,3;
                -fx-background-radius: 3,2,2,2;
                -fx-padding: 12 30 12 30;
                -fx-text-fill: white;
                -fx-font-size: 12px;
                }
                """;

        root.getStyleClass().add(buttonStyle);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                var button = new Button("Button (C:%d, R:%d)".formatted(col, row));
                button.setMaxWidth(Double.MAX_VALUE);
                root.add(button, col, row);
            }
        }

        var cc1 = new ColumnConstraints(150);

        var cc2 = new ColumnConstraints();
        cc2.setPercentWidth(30);
        cc2.setHalignment(HPos.CENTER);

        var cc3 = new ColumnConstraints();
        cc3.setPercentWidth(35);

        root.getColumnConstraints().addAll(cc1, cc2, cc3);

        var rc1 = new RowConstraints();
        rc1.setPercentHeight(35);
        rc1.setValignment(VPos.TOP);

        var rc2 = new RowConstraints();
        rc2.setPercentHeight(35);
        rc2.setValignment(VPos.BOTTOM);

        root.getRowConstraints().addAll(rc1, rc2);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("GridPane Column/Row Constraints Demo");
        stage.show();
    }
}

