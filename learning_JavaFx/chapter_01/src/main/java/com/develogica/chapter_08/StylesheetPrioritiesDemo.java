package com.develogica.chapter_08;

import com.develogica.util.ResourceUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class StylesheetPrioritiesDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var yesButton = new Button("Yes");
        var noButton = new Button("No");
        var cancelButton = new Button("Cancel");

        yesButton.setStyle("-fx-font-size: 16px;");
        yesButton.setFont(new Font(10));

        noButton.setFont(new Font(8));

        var root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(10);
        root.getChildren().addAll(yesButton, noButton, cancelButton);

        var scene = new Scene(root);

        var url = ResourceUtil.getResourceURLString("css/stylesPriorities.css");
        scene.getStylesheets().add(url);

        stage.setScene(scene);
        stage.setTitle("Stylesheet-Priorities Demo");
        stage.show();
    }
}

