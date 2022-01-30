package com.develogica.improved;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        greetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                var name = nameFld.getText().trim();
                if (name.length() > 0) {
                    msg.setText("Hello " + name);
                } else {
                    msg.setText("Hello there");
                }
            }
        });

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
