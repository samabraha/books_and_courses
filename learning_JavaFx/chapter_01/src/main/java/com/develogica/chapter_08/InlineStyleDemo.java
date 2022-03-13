package com.develogica.chapter_08;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class InlineStyleDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var yesButton = new Button("yes");
        yesButton.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");

        var noButton = new Button("no");
        var cancelButton = new Button("cancel");

        var openButton = new Button("open");
        var saveButton = new Button("save");
        var closeButton = new Button("close");
        closeButton.setOnAction(event -> Platform.exit());

        var vBox1 = new VBox();
        vBox1.setPadding(new Insets(10));
        vBox1.setStyle("-fx-border-width: 4.0; -fx-border-color: teal ;");
        vBox1.getChildren().addAll(yesButton, noButton, cancelButton);

        var vBox2 = new VBox();
        vBox2.setPadding(new Insets(10));
        vBox2.setStyle("-fx-border-width: 4.0; -fx-border-color: blue ;");
        vBox2.getChildren().addAll(openButton, saveButton, closeButton);

        var root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(vBox1, vBox2);

        root.setStyle("-fx-border-color: navy; -fx-border-width: 10.0;");
        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("InlineStyle Demo");
        stage.show();
    }
}
