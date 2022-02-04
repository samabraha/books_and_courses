package com.develogica.chapter_02.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CenteredCircle extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle();
        Group root = new Group(circle);
        Scene scene = new Scene(root, 400, 300);

        circle.centerXProperty()
                .bind(scene.widthProperty().divide(2));
        circle.centerYProperty()
                .bind(scene.heightProperty().divide(2));
        circle.radiusProperty()
                .bind(Bindings.min(scene.widthProperty(), scene.heightProperty())
                        .divide(2));

        stage.setTitle("Binding in JavaFx");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }
}
