package com.develogica.chapter_12;

import com.develogica.util.ColorUtil;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SliderDemo extends Application {
    Rectangle rect = new Rectangle(0, 0, 200, 50);
    Slider redSlider = getSlider();
    Slider greenSlider = getSlider();
    Slider blueSlider = getSlider();


    String zerosStr = "000";
    Label redLabel = new Label(zerosStr);
    Label greenLabel = new Label(zerosStr);
    Label blueLabel = new Label(zerosStr);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var labelStyleStr = """
                -fx-padding: 5;
                -fx-font-size: 20;
                """;


        redSlider.valueProperty().addListener(this::changed);
        greenSlider.valueProperty().addListener(this::changed);
        blueSlider.valueProperty().addListener(this::changed);

        redLabel.setStyle(labelStyleStr);
        redSlider.valueProperty().addListener(listener ->
        {
            redLabel.setText("%03.0f".formatted(redSlider.getValue()));
            redLabel.setTextFill(Color.rgb((int)redSlider.getValue(), 0, 0));
        });

        greenLabel.setStyle(labelStyleStr);
        greenSlider.valueProperty().addListener(listener ->
        {
            greenLabel.setText("%03.0f".formatted(greenSlider.getValue()));
            greenLabel.setTextFill(Color.rgb(0, (int)greenSlider.getValue(), 0));
        });

        blueLabel.setStyle(labelStyleStr);
        blueSlider.valueProperty().addListener(listener ->
        {
            blueLabel.setText("%03.0f".formatted(blueSlider.getValue()));
            blueLabel.setTextFill(Color.rgb(0, 0, (int)blueSlider.getValue()));
        });


        var randomColorButton = new Button("Random Color");
        randomColorButton.setOnAction(event -> {
            var color = ColorUtil.getRandomColor();
            rect.setFill(color);

            redSlider.setValue(color.getRed() * 255);
            greenSlider.setValue(color.getGreen() * 255);
            blueSlider.setValue(color.getBlue() * 255);
        });

        var grid = new GridPane();
        grid.setVgap(10);
        grid.add(rect, 0, 0, 2, 1);
        grid.add(new Label("Use sliders to change the fill color."), 0, 1, 2, 1);
        grid.addRow(2, new Label("Red"), redSlider, new Separator(Orientation.VERTICAL), redLabel);
        grid.addRow(3, new Label("Green"), greenSlider, new Separator(Orientation.VERTICAL), greenLabel);
        grid.addRow(4, new Label("Blue"), blueSlider, new Separator(Orientation.VERTICAL), blueLabel);
        grid.addRow(5, randomColorButton);

        grid.setPadding(new Insets(10));

        var distanceLabel = new Label();

        var distanceSlider = new Slider();
        distanceSlider.setMin(0d);
        distanceSlider.setMax(100d);
        distanceSlider.setShowTickMarks(true);
        distanceSlider.setShowTickLabels(true);
        distanceSlider.setSnapToTicks(true);

        distanceSlider.valueProperty().addListener(value ->
                distanceLabel.setText("%.2f".formatted(distanceSlider.getValue())));

        var root = new VBox(distanceLabel, distanceSlider, grid);
        root.setPadding(new Insets(10));

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Slider Demo");
        stage.show();

        changeColor();
    }

    private void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        changeColor();
    }

    private void changeColor() {
        var rVal = (int) redSlider.getValue();
        var gVal = (int) greenSlider.getValue();
        var bVal = (int) blueSlider.getValue();

        var color = Color.rgb(rVal, gVal, bVal);


        rect.setFill(color);
    }

    private Slider getSlider() {
        var slider = new Slider(0, 255, 125);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(64);
        slider.setMinorTickCount(4);
        slider.setBlockIncrement(32);
        slider.setSnapToTicks(true);

        return slider;
    }
}
