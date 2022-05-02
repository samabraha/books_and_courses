package com.develogica.chapter_10;

import com.develogica.util.ColorUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TextFlowDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var text1 = new Text("I, ");

        var radioButton1 = new RadioButton("Mr.");
        radioButton1.setSelected(true);
        var radioButton2 = new RadioButton("Ms.");

        var group = new ToggleGroup();
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);

        var nameField = new TextField();
        nameField.setPromptText("Your name");

        var text2 = new Text("""
                , acknowledge the receipt of the letter...
                
                Sincerely
                
                """);

        var submitButton = new Button("Submit Form");

        var root = new TextFlow(text1, radioButton1, radioButton2, nameField, text2, submitButton);
        root.setPrefWidth(350);
        root.setLineSpacing(5);

        var color = ColorUtil.getCssRgbFunctionString(
                ColorUtil.getRandomColorForBrightness(40)
        );

        var fontColor = ColorUtil.getCssRgbFunctionString(
                ColorUtil.getRandomColorForBrightness(60)
        );

        root.setStyle("""
                -fx-padding: 10;
                -fx-font-size: 16;
                -fx-fill-color: %s;
                -fx-font-size: 16;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: %s;
                """.formatted(fontColor, color));

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("TextFlow Demo");
        stage.show();
    }
}
