package com.develogica.chapter_06;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MicroHelpApp extends Application {

    private Text helpText = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var firstName = new TextField();
        var lastName = new TextField();
        var salary = new TextField();
        var closeButton = new Button("Close");

        closeButton.setOnAction(event -> Platform.exit());

        firstName.getProperties().put("microHelpText", "Enter the first name");
        lastName.getProperties().put("microHelpText", "Enter the last name");
        salary.getProperties().put("microHelpText", "Enter a salary grater than 2,000.00");

        helpText.setManaged(false);
        helpText.setTextOrigin(VPos.TOP);
        helpText.setFill(Color.RED);
        helpText.setFont(Font.font(null, 9));
        helpText.setMouseTransparent(true);

        var root = new GridPane();
        root.add(new Label("First Name"), 1, 1);
        root.add(firstName, 2, 1);

        root.add(new Label("Last Name"), 1, 2);
        root.add(lastName, 2, 2);

        root.add(new Label("Salary"), 1, 3);
        root.add(salary, 2, 3);

        root.add(closeButton, 3, 3);
        root.add(helpText, 4, 3);

        var scene = new Scene(root, 300, 100);

        scene.focusOwnerProperty().addListener(this::focusChanged);

        stage.setScene(scene);
        stage.setTitle("MicroHelp App");
        stage.show();
    }

    private void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode) {
        String microHelpText = (String) newNode.getProperties().get("microHelpText");
        if (microHelpText != null && microHelpText.trim().length() > 0) {
            helpText.setText(microHelpText);
            helpText.setVisible(true);

            double x = newNode.getLayoutX() + newNode.getLayoutBounds().getMinX()
                    - helpText.getLayoutBounds().getMinX();
            double y = newNode.getLayoutY() + newNode.getLayoutBounds().getMinY()
                    + newNode.getLayoutBounds().getHeight() - helpText.getLayoutBounds().getMinX();


            helpText.setLayoutX(x);
            helpText.setLayoutY(y);
            helpText.setWrappingWidth(newNode.getLayoutBounds().getWidth());

        } else {
            helpText.setVisible(false);
        }
    }
}
