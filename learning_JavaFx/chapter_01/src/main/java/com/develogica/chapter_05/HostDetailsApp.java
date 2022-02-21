package com.develogica.chapter_05;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;

public class HostDetailsApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String yahooUrl = "https://www.yahoo.com";

        var openUrlButton = new Button("Go to Yahoo!");
        openUrlButton.setOnAction(event -> getHostServices().showDocument(yahooUrl));

        var showAlertButton = new Button("Show Alert");
        showAlertButton.setOnAction(event -> showAlert(stage));

        var root = new VBox();
        root.getChildren().addAll(openUrlButton, showAlertButton);

        Map<String, String> hostDetails = getHostDetails();
        for (var entrySet : hostDetails.entrySet()) {

            var desc = String.format("%s : %s", entrySet.getKey(), entrySet.getValue());
            root.getChildren().add(new Label(desc));
        }

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Know the Host Environment");
        stage.show();
    }

    private Map<String, String> getHostDetails() {
        Map<String, String> map = new HashMap<>();
        var host = getHostServices();

        String codeBase = host.getCodeBase();
        map.put("codeBase", codeBase);
        String documentBase = host.getDocumentBase();
        map.put("documentBase", documentBase);
        String splashImageUri = host.resolveURI(documentBase, "splash.jpg");
        map.put("splashImageUri", splashImageUri);

        return map;
    }

    private void showAlert(Stage owner) {
        Stage s = new Stage(StageStyle.UTILITY);
        s.initOwner(owner);
        s.initModality(Modality.WINDOW_MODAL);
        var root = new VBox();
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setPadding(new Insets(0, 5,  20, 5));

        var message = """
                This is an FX Alert with a new message.
                This is second line of message.
                Then comes the third line.
                And a final one.
                """;

        message.lines().forEach(line -> root.getChildren().add(new Label(line)));

        Button okButton = new Button("OK");
        okButton.setPadding(new Insets(5));
        okButton.setOnAction(event -> s.close());

        root.getChildren().add(okButton);

        Scene scene = new Scene(root);
        Node n = new Group();

        s.setScene(scene);
        s.setTitle("FX Alert");
        s.show();
        s.setResizable(false);
    }
}
