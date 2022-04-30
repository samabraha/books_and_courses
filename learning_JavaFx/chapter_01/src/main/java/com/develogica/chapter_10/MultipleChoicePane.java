package com.develogica.chapter_10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

public class MultipleChoicePane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)  {

        Font font = Font.loadFont(Path.of("gezed.ttf").toUri().toString(), 15);

        if (font == null) {
            System.out.println("Did not load");
        }

        Text text = new Text("ድሮን");
        text.setFont(font);

        var choicesBox = new VBox(text);

        var rnd = ThreadLocalRandom.current();

        for (int i = 1; i <= 5; i++) {
            var button = new Button("Button " + i);

            int hue = rnd.nextInt(72) * 5;
            var backColor = Color.hsb(hue, .5, .6);

            int r = (int) (backColor.getRed() * 255);
            int g = (int) (backColor.getGreen() * 255);
            int b = (int) (backColor.getBlue() * 255);

            var fontColor = Color.hsb((hue + 180) % 360, .8, 1.0);
//            var fontColor = backColor.invert();

            button.setTextFill(fontColor);
            button.setStyle("""
                    -fx-font-size: %d;
                    -fx-font-style: oblique;
                    -fx-background-color: rgb(%d, %d, %d);
                    """.formatted(20, r, g, b)
            );
            choicesBox.getChildren().addAll(button);
        }

        var qVBox = new VBox(choicesBox);

        var root = new BorderPane();
        root.setCenter(qVBox);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Multiple-Choice Pane");
        stage.show();
    }
}
