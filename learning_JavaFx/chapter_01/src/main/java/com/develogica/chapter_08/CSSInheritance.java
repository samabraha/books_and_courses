package com.develogica.chapter_08;

import com.develogica.chapter_07.ColourfulRectangles;
import com.develogica.util.ResourceUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CSSInheritance extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var okButton = new Button("OK");

        var cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> Platform.exit());

        var myPane = new Pane();
        myPane.setPrefSize(100, 100);
        myPane.setStyle("""
                -fx-background-color: %s, %s, %s ;
                -fx-background-insets: 5, 10, 20;
                -fx-background-radius: 10, 10, 10;
                """.formatted(
                        ResourceUtil.gerRandomColorString(),
                        ResourceUtil.gerRandomColorString(),
                        ResourceUtil.gerRandomColorString()
        ));

        var root = new HBox(10);
        var stylesheet = ResourceUtil.getResourceURLString("css/buttonstyles.css");
        root.getStylesheets().add(stylesheet);
        root.getChildren().addAll(okButton, cancelButton, myPane);
        root.setStyle("""
                -fx-cursor: hand;
                -fx-border-color: blue;
                -fx-border-width: 5px;
                """);

        okButton.setStyle("""
                -fx-rotate: 15deg;
                -fx-border-color: teal;
                -fx-border-width: inherit;
                """);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CSS Inheritance Demo");
        stage.show();
    }
}
