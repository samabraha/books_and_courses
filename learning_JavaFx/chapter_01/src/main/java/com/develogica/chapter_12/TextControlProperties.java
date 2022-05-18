package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class TextControlProperties extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var nameTextField = new TextField();

//        var contextMenu = new ContextMenu(new MenuItem("Click"), new MenuItem("Press"));
//        nameTextField.setContextMenu(contextMenu);

        var anchorLabel = new Label("");
        var caretLabel = new Label("");
        var lengthLabel = new Label("");
        var selectedTextLabel = new Label("");
        var selectionLabel = new Label("");
        var textLabel = new Label("");

        anchorLabel.textProperty().bind(nameTextField.anchorProperty().asString());
        caretLabel.textProperty().bind(nameTextField.caretPositionProperty().asString());
        lengthLabel.textProperty().bind(nameTextField.lengthProperty().asString());
        selectedTextLabel.textProperty().bind(nameTextField.selectedTextProperty());
        selectionLabel.textProperty().bind(nameTextField.selectionProperty().asString());
        textLabel.textProperty().bind(nameTextField.textProperty());

        var root = new GridPane();
        root.setHgap(10);
        root.setVgap(5);

        root.addRow(0, new Label("Name"), nameTextField);
        root.addRow(1, new Label("Anchor Position"), anchorLabel);
        root.addRow(2, new Label("Caret Position"), caretLabel);
        root.addRow(3, new Label("Length"), lengthLabel);
        root.addRow(4, new Label("Selected Text"), selectedTextLabel);
        root.addRow(5, new Label("Selection"), selectionLabel);
        root.addRow(6, new Label("Text"), textLabel);

        root.getStyleClass().add("medium-border");

        var scene = new Scene(root);
        var sheet = Objects.requireNonNull(getClass().getResource("/css/lineStyles.css")).toString();
        scene.getStylesheets().add(sheet);
        stage.setScene(scene);
        stage.setTitle("TextControl Properties Demo");
        stage.show();
    }
}
