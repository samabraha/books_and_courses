package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MnemonicDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        for (int i = 32; i < 123; i++) {
            if (i % 6 == 0) System.out.println();
            System.out.printf("%d %s %s \t", i, Integer.toBinaryString(i), (char) i);
        }

        var messageLabel = new Text("""
                Press Ctrl + X on Windows and
                Meta + X on Mac to close the window""");
        var label = new Label("Press Alt + 1 or Alt + 2");

        var button1 = new Button("Button _1");
        button1.setOnAction(event -> label.setText("Button 1 clicked"));

        var button2 = new Button("Button 2");
        button2.setOnAction(event -> label.setText("Button 2 clicked"));

        var root = new VBox(10);
        root.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: blue;
                """);
        root.getChildren().addAll(messageLabel, label, button1, button2);

        var scene = new Scene(root);

        var keyCodeCombo = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.ALT_DOWN);
        var mnemonic = new Mnemonic(button2, keyCodeCombo);

        scene.addMnemonic(mnemonic);

        var keyCodeCombo2 = new KeyCodeCombination(KeyCode.X, KeyCombination.ALT_DOWN);
        Runnable task = () -> scene.getWindow().hide();

        scene.getAccelerators().put(keyCodeCombo2, task);

        stage.setScene(scene);
        stage.setTitle("Mnemonic Demo");
        stage.show();
    }
}
