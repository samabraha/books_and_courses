package com.develogica.chapter_08;

import com.develogica.util.ResourceUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonStyleSheet extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("getUserAgentStylesheet() = " + getUserAgentStylesheet());
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        Button cancelBtn = new Button("Cancel");

        cancelBtn.setOnAction(event -> Platform.exit() );

        HBox root = new HBox();
        root.getChildren().addAll(yesBtn, noBtn, cancelBtn);

        Scene scene = new Scene(root);
        String url = ResourceUtil.getResourceURLString("css/buttonstyles.css");
        scene.getStylesheets().add(url);

        stage.setScene(scene);
        stage.setTitle("Button StyleSheet");
        stage.show();
    }
}
