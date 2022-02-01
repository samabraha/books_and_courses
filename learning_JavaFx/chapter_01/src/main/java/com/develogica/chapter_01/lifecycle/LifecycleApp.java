package com.develogica.chapter_01.lifecycle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LifecycleApp extends Application {
    public static void main(String[] args) {
        System.out.printf("main(): from %s%n", Thread.currentThread().getName());
        launch(args);
        System.out.printf("main() again: from %s%n", Thread.currentThread().getName());
    }

    public LifecycleApp() {
        System.out.printf("LifecycleApp():init from %s%n", Thread.currentThread().getName());
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.printf("init() from %s%n", Thread.currentThread().getName());
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.printf("start() from %s%n", Thread.currentThread().getName());
        Scene scene = new Scene(new Group(), 200, 300);
        scene.setFill(new Color(.5, .3, .8, 1.0 ));
        stage.setScene(scene);
        stage.setTitle("Lifecycle Application");
        stage.show();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.printf("stop() from %s%n", Thread.currentThread().getName());
    }
}
