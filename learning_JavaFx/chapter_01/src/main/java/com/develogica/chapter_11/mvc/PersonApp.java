package com.develogica.chapter_11.mvc;

import com.develogica.chapter_11.mvc.model.Person;
import com.develogica.chapter_11.mvc.view.PersonPresenter;
import com.develogica.chapter_11.mvc.view.PersonView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PersonApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var model = new Person();
        var dateFormat = "MM/dd/yyyy";

        var view = new PersonView(model, dateFormat);

        var scene = new Scene(view);

        var presenter = new PersonPresenter(model, view);
        view.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: blue;
                """);

        stage.setScene(scene);
        stage.setTitle("Person Management");
        stage.show();
    }
}
