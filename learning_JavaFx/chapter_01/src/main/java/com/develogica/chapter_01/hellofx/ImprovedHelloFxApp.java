package com.develogica.chapter_01.hellofx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImprovedHelloFxApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label nameLbl = new Label("Enter your name:");
        TextField nameFld = new TextField();
        Label msg = new Label();
        msg.setStyle("-fx-text-fill: blue;");

        Button greetBtn = new Button("Greet");
        Button exitBtn = new Button("Exit");
        greetBtn.setOnAction(event -> {
                var name = nameFld.getText().trim();
                msg.setText(
                        String.format("Hello, %s!", name.length() > 0 ? name : "there")
                );
            }
        );

        exitBtn.setOnAction(event -> Platform.exit());

        var root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(nameLbl, nameFld, msg, greetBtn, exitBtn);
        Scene scene = new Scene(root, 350, 150);
        stage.setScene(scene);
        stage.setTitle("Improved Hello World!");
        stage.show();

    }
}
