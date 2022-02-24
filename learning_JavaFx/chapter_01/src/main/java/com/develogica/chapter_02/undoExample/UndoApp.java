package com.develogica.chapter_02.undoExample ;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.util.ArrayDeque;

public class UndoApp extends Application {

    private final ArrayDeque<UIAction> history = new ArrayDeque<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));

        primaryStage.show();
    }

    private Parent createContent() {
        var root = new VBox();
        root.setPrefSize(800, 600);

        var inputField = new TextField("");
        inputField.setFont(Font.font(22));

        var btnAdd = new Button("Add");
        btnAdd.setFont(Font.font(20));

        btnAdd.setOnAction(event -> {
            var action = new AddText(inputField.getText(), root);

            run(action);

            action.uiText.setOnMouseClicked(event1 -> run(new RemoveText(action.uiText, root)));
        });

        var btnUndo = new Button("Undo");
        btnUndo.setFont(Font.font(20));
        btnUndo.setOnAction(event -> undo());

        root.getChildren().addAll(inputField, btnAdd, btnUndo);
        return root;
    }

    private void run(UIAction action) {
        history.add(action);

        action.run();
    }

    private void undo() {
        if( history.isEmpty()) {
            return;
        }
        var lastAction = history.removeLast();
        lastAction.undo();
    }

    class AddText implements UIAction {
        String text;
        Pane root;

        Text uiText;

        public AddText(String text, Pane root) {
            this.text = text;
            this.root = root;
        }

        @Override
        public void run() {
            uiText = new Text(text);
            uiText.setFont(Font.font(22));

            root.getChildren().add(uiText);
        }

        @Override
        public void undo() {
            root.getChildren().remove(uiText);
        }
    }

    class RemoveText implements UIAction {
        Text uiText;
        Pane root;

        public RemoveText(Text uiText, Pane root) {
            this.uiText = uiText;
            this.root = root;

        }

        @Override
        public void run() {
            root.getChildren().remove(uiText);
        }

        @Override
        public void undo() {
            root.getChildren().add(uiText);
        }
    }

    interface UIAction {
        void run();
        void undo();
    }
}
