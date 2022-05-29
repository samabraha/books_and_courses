package com.develogica.chapter_12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class TabbedPaneDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var root = new TabPane();

        var style = """
                .html-editor .button, .html-editor .toggle-button {
                    -fx-background-color: red;
                }
                """;

        var htmlEditor = new HTMLEditor();
        htmlEditor.getStyleClass().add(style);

        var editorTab = new Tab("HTML", htmlEditor);

        var summaryTab = new Tab("Summary", new HBox());

        root.getTabs().addAll(editorTab, summaryTab);

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TabbedPane Demo");
        stage.show();
    }
}
