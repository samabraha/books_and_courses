package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ColorGUI extends Application {
    enum Caller { RGB, HSB }
    Slider redSlider = getRGBSlider();
    Slider greenSlider = getRGBSlider();
    Slider blueSlider = getRGBSlider();

    Slider hueSlider = new Slider(0, 360, 180);
    Slider saturationSlider = new Slider(0, 100, 50);
    Slider brightnessSlider = new Slider(0, 100, 50);
    Rectangle colorRect = new Rectangle(300, 75);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        var buttonBox = GUIFactory.getButtons(null, "OK", "Cancel");

        var root = new VBox(colorRect, createRGBGridPane(), createHSBGridPane(), buttonBox);
        root.setPadding(new Insets(5));

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private Label getLabel(String labelStr) {
        var label = new Label(labelStr);
        label.setFont(Font.font(16));
        return label;
    }

    private Slider getRGBSlider() {
        var slider = new Slider(0, 255, 100);
        slider.setMajorTickUnit(32);
        slider.setMinorTickCount(8);

        slider.setBlockIncrement(32);
        slider.setSnapToTicks(true);

        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider.setPrefWidth(280);
        return slider;
    }

    private GridPane createRGBGridPane() {
        var redLabel = getLabel("red");
        var greenLabel = getLabel("green");
        var blueLabel = getLabel("blue");

        var redValueLabel = getLabel("");
        var greenValueLabel = getLabel("");
        var blueValueLabel = getLabel("");

        redSlider.valueProperty().addListener(listener ->
        {
            redValueLabel.setText("%.0f".formatted(redSlider.getValue()));
            changeColor(Caller.RGB);
        });

        greenSlider.valueProperty().addListener(listener ->
        {
            greenValueLabel.setText("%.0f".formatted(greenSlider.getValue()));
            changeColor(Caller.RGB);
        });

        blueSlider.valueProperty().addListener(listener ->
        {
            blueValueLabel.setText("%.0f".formatted(blueSlider.getValue()));
            changeColor(Caller.RGB);
        });

        var rgbGrid = new GridPane();
        rgbGrid.addRow(1, redLabel, redSlider, redValueLabel);
        rgbGrid.addRow(2, greenLabel, greenSlider, greenValueLabel);
        rgbGrid.addRow(3, blueLabel, blueSlider, blueValueLabel);

        return rgbGrid;
    }


    private void changeColor(Caller caller) {
        Color color = Color.GRAY;
        switch (caller) {
            case RGB -> {
                var redVal = (int) redSlider.getValue();
                var greenVal = (int) greenSlider.getValue();
                var blueVal = (int) blueSlider.getValue();
                color = Color.rgb(redVal, greenVal, blueVal);
            } case HSB -> {
                var hVal = hueSlider.getValue();
                var sVal = saturationSlider.getValue() / 100;
                var bVal = brightnessSlider.getValue() / 100;
                color = Color.hsb(hVal, sVal, bVal);
            }
        }

        redSlider.setValue(color.getRed() * 255);
        greenSlider.setValue(color.getGreen() * 255);
        blueSlider.setValue(color.getBlue() * 255);

        hueSlider.setValue(color.getHue());
        saturationSlider.setValue(color.getSaturation() * 100);
        brightnessSlider.setValue(color.getBrightness() * 100);

        colorRect.setFill(color);
    }
    private GridPane createHSBGridPane() {
        var hueLabel = getLabel("hue");
        var saturationLabel = getLabel("saturation");
        var brightnessLabel = getLabel("brightness");

        var hueValueLabel = getLabel("180");
        var saturationValueLabel = getLabel("50");
        var brightnessValueLabel = getLabel("50");

        hueSlider.valueProperty().addListener(listener ->
        {
            hueValueLabel.setText("%.0f".formatted(hueSlider.getValue()));
            changeColor(Caller.HSB);
        });

        saturationSlider.valueProperty().addListener(listener ->
        {
            saturationValueLabel.setText("%.0f".formatted(saturationSlider.getValue()));
            changeColor(Caller.HSB);
        });

        brightnessSlider.valueProperty().addListener(listener ->
        {
            brightnessValueLabel.setText("%.0f".formatted(brightnessSlider.getValue()));
            changeColor(Caller.HSB);
        });


        var hsbGrid = new GridPane();
        hsbGrid.addRow(1, hueLabel, hueSlider, hueValueLabel);
        hsbGrid.addRow(2, brightnessLabel, brightnessSlider, brightnessValueLabel);
        hsbGrid.addRow(3, saturationLabel, saturationSlider, saturationValueLabel);

        return hsbGrid;
    }


}
