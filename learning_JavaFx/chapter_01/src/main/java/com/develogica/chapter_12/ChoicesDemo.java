package com.develogica.chapter_12;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ChoicesDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){

        ObservableList<String> animals =
                FXCollections.observableArrayList("Antelope", "Bear", "Camel", "Deer", "Elephant", "Fox");

        var choiceBox = new ChoiceBox<>(animals);
//        choiceBox.getItems().addAll(List.of("Antelope", "Bear", "Camel", "Deer", "Elephant", "Fox", "Giraffe"));
        choiceBox.getSelectionModel().selectFirst();

        var selectionLabel = new Label();
        selectionLabel.textProperty().bind(choiceBox.getSelectionModel().selectedItemProperty());

        var showButton = new Button("Show");
        showButton.setOnAction(event -> choiceBox.show());

        var nextButton = new Button("Next");
        nextButton.setOnAction( event -> moveSelection(choiceBox, true) );

        var prevButton = new Button("Prev");
        prevButton.setOnAction( event -> moveSelection(choiceBox, false) );

        var buttonBox = new VBox(10, showButton, nextButton, prevButton);

        var root = new TilePane(10, 10, selectionLabel, choiceBox, buttonBox);
        root.setPadding(new Insets(10));

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Choices Demo");
        stage.show();
    }

    private void moveSelection(ChoiceBox<String> choiceBox, boolean forward) {
        var model = choiceBox.getSelectionModel();

        if (forward && model.isSelected(choiceBox.getItems().size() - 1)) {
            model.selectFirst();
        } else if (!forward && model.isSelected(0)) {
            model.selectLast();
        } else if (forward) {
            model.selectNext();
        } else {
            model.selectPrevious();
        }
    }
}
