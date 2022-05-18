package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventType;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.time.LocalDate;

public class Combos extends Application {
    Label scrollLabel = new Label();
    ScrollBar hScroll = new ScrollBar();
    ScrollBar vScroll = new ScrollBar();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){

        var colorPicker = new ColorPicker(Color.RED);

        var datePicker = new DatePicker(LocalDate.now());
        datePicker.setShowWeekNumbers(false);
        datePicker.setEditable(false);

        hScroll.setOrientation(Orientation.HORIZONTAL);
        hScroll.setUnitIncrement(5);
        hScroll.setBlockIncrement(20);
        hScroll.addEventHandler(EventType.ROOT, event -> updateScrollValues());

        vScroll.setOrientation(Orientation.VERTICAL);
        vScroll.setUnitIncrement(5);
        vScroll.setBlockIncrement(20);
        vScroll.addEventHandler(EventType.ROOT, event -> updateScrollValues());

        var nestButton = new Button("Nested Calls");
        nestButton.setOnAction(event -> {
            System.out.println(x("A", 1) +
                    x("B", (x("C", 1) + x("D", 1) + x("E", 1))) +
                    x("F", 1));
        });

        var nestLabel = new Label();

        var root = new FlowPane(colorPicker, datePicker, hScroll, vScroll, scrollLabel, nestButton, nestLabel);
        root.setHgap(10);
        root.setVgap(10);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Combos Demo");
        stage.show();
    }

    private void updateScrollValues() {
        var text = "H:%.0f V:%.0f%n".formatted(hScroll.getValue(), vScroll.getValue());
        scrollLabel.setText(text);
    }

    int x(String msg, int val) {
        System.out.print(msg);
        return val;
    }
}

