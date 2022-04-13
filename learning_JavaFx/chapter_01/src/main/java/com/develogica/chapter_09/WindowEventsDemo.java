package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// */
public class WindowEventsDemo extends Application {
    CheckBox canCloseCheckBox = new CheckBox("Can Close Window");
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        var closeButton = new Button("Close");
        closeButton.setOnAction(event -> stage.close());

        var hideButton = new Button("Hide");
        hideButton.setOnAction(event -> { showDialog(stage); stage.hide(); });

        var root= new HBox();
        root.setSpacing(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(canCloseCheckBox, hideButton, closeButton);

        var scene = new Scene(root);

        stage.setOnShowing(this::handle);
        stage.setOnShown(this::handle);
        stage.setOnHiding(this::handle);
        stage.setOnHidden(this::handle);
        stage.setOnCloseRequest(this::handle);

        stage.setScene(scene);
        stage.setTitle("WindowEvents Demo");
        stage.show();
    }

    private void showDialog(Stage mainWindow) {
        var popup = new Stage();
        var closeButton = new Button("Click to show main Window");
        closeButton.setOnAction(event -> {
            popup.close();
            mainWindow.show();
        });

        var root = new HBox();
        root.setSpacing(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(closeButton);

        var scene = new Scene(root);

        popup.setScene(scene);
        popup.setTitle("");
        popup.show();
    }

    private void handle(WindowEvent event) {
        var type = event.getEventType();
        if (type == WindowEvent.WINDOW_CLOSE_REQUEST && canCloseCheckBox.isSelected()) {
            event.consume();
        }

        System.out.printf("%s: consumed: %s%n", type, event.isConsumed());
    }
}
