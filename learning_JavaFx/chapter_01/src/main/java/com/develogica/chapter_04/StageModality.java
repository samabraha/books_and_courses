package com.develogica.chapter_04;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import static javafx.stage.Modality.*;
import static javafx.stage.Modality.APPLICATION_MODAL;

public class StageModality extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        startStage(stage);

        StageModality stageModality = new StageModality();
        var hs = stageModality.getHostServices();
        System.out.println("hs.getCodeBase() = " + hs.getCodeBase());
        System.out.println("hs.getDocumentBase() = " + hs.getDocumentBase());
        hs.showDocument("http://www.google.com");
        System.out.println("Done");
    }

    private void startStage(Stage stage) {
        Button ownedNoneButton = new Button("Owned none");
        ownedNoneButton.setOnAction(event -> showDialog(stage, NONE));


        Button nonOwnedNoneButton = new Button("Non-owned None");
        nonOwnedNoneButton.setOnAction(event -> showDialog(null, NONE));

        Button ownedWinButton = new Button("Owned Window Modal");
        ownedWinButton.setOnAction(event -> showDialog(stage, WINDOW_MODAL));

        Button nonOwnedWinButton = new Button("Non-owned Window Modal");
        nonOwnedWinButton.setOnAction(event -> showDialog(null, WINDOW_MODAL));

        Button ownedAppButton = new Button("Owned Application Modal");
        ownedAppButton.setOnAction(event -> showDialog(stage, APPLICATION_MODAL));

        Button nonOwnedAppButton = new Button("Non-owned Application Modal");
        nonOwnedAppButton.setOnAction(event -> showDialog(null, APPLICATION_MODAL));

        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(event -> Platform.exit());

        var root = new VBox();
        root.getChildren().addAll(
                ownedNoneButton, nonOwnedNoneButton,
                ownedWinButton, nonOwnedWinButton,
                ownedAppButton, nonOwnedAppButton,
                exitButton
        );

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("The Primary Stage");
        stage.show();
        stage.setOpacity(0.0);

        stage.setFullScreen(true);
        stage.setMinWidth(nonOwnedAppButton.getWidth());
        stage.setMinHeight(exitButton.getHeight() * 7);
    }

    @Override
    public void init() throws Exception {
        System.out.println("init() " + Thread.currentThread().getName());
        Runnable task = () -> {
            System.out.println("Running a task on the " + Thread.currentThread().getName());
        };

        Platform.runLater(task);
    }

    /** */
    private void showDialog(Window owner, Modality modality) {
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(modality);
        Label modalityLabel = new Label(modality.toString());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> stage.close());

        var root = new VBox();
        root.getChildren().addAll(modalityLabel, closeButton);

        var scene = new Scene(root, 300, 200);
        stage.setScene(scene);

        stage.setTitle("A Dialog Box");
        stage.setOpacity(.5);
        stage.show();
    }
}

