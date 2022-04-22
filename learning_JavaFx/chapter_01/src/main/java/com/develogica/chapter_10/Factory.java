package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Factory extends Application {
    private final List<Button> buttons = new ArrayList<>();
    private final List<Label> labels = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var factory = new Factory();
        Scene scene = factory.getFormScene(4);
        factory.getLabels().forEach(label -> label.setStyle("-fx-background-color: rgb(255, 200, 100); "));
        factory.getButtons().forEach(button -> button.setOnAction(event -> Platform.exit()));

        stage.setScene(scene);
        stage.setTitle("Scene Factory Demo");
        stage.show();
    }

    public Scene getFormScene(int number) {


        VBox root = new VBox(5);

        for (int i = 0; i < number; i++) {
            var label = new Label("Label " + (i + 1));
            root.getChildren().add(label);
            labels.add(label);

            var field = new TextField();
            root.getChildren().add(field);
        }

        var buttonHBox = new HBox(10);

        var button1 = new Button("OK");
        buttonHBox.getChildren().add(button1);
        buttons.add(button1);

        var button2 = new Button("Cancel");
        buttonHBox.getChildren().add(button2);
        buttons.add(button1);

        root.getChildren().add(buttonHBox);
        return new Scene(root);
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public List<Label> getLabels() {
        return labels;
    }
}
