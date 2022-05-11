package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

public class ListViewDemo extends Application {
    Map<String, ObservableValue<Boolean>> fruits = new HashMap<>();
    ListView<String> chosen = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        fruits.put("Apple", new SimpleBooleanProperty(false));
        fruits.put("Banana", new SimpleBooleanProperty(false));
        fruits.put("Orange", new SimpleBooleanProperty(false));
        fruits.put("Guava", new SimpleBooleanProperty(false));

        ListView<String> breakfasts = new ListView<>();
        breakfasts.setPrefSize(200, 120);
        breakfasts.setEditable(false);
        breakfasts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        breakfasts.getItems().addAll(fruits.keySet());

        Callback<String, ObservableValue<Boolean>> itemToBoolean = fruits::get;

        breakfasts.setCellFactory(CheckBoxListCell.forListView(itemToBoolean));

        var printButton = new Button("Print Selection");
        printButton.setOnAction(event -> printSelection());

        var breakfastsVBox = new VBox(new Label("Breakfasts"), breakfasts, printButton, chosen);

        var root = new FlowPane(breakfastsVBox);
        root.setVgap(10);
        root.setHgap(10);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("ListView Demo");
        stage.show();
    }

    private void printSelection() {
        chosen.getItems().clear();
        System.out.println("Selected items: ");
        for (var entry: fruits.entrySet()) {
            if (entry.getValue().getValue()) {
                chosen.getItems().add(entry.getKey());
                System.out.println(entry.getKey());
            }
        }
    }
}
