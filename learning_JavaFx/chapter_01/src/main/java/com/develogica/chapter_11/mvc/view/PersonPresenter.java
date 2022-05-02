package com.develogica.chapter_11.mvc.view;

import com.develogica.chapter_11.mvc.model.Person;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class PersonPresenter {
    private final Person model;
    private final PersonView view;

    public PersonPresenter(Person model, PersonView view) {
        this.model = model;
        this.view = view;
        attachEvents();
    }

    private void attachEvents() {
        view.birthDateTxtFld.setOnAction(event -> handleBirthDateChange());
        view.birthDateTxtFld.getScene().focusOwnerProperty().addListener(this::focusChanged);

        view.saveButton.setOnAction(event -> saveData());

        view.closeButton.setOnAction(event -> view.getScene().getWindow().hide());
    }

    private void saveData() {
        List<String> errorList = new ArrayList<>();
        boolean isSaved = model.save(errorList);
        if (!isSaved) {
            this.showError(errorList);
        }
    }

    private void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode ) {
        if (oldNode == view.birthDateTxtFld) {
            handleBirthDateChange();
        }
    }

    private void handleBirthDateChange() {
        String birthDateStr = view.birthDateTxtFld.getText();

        if (birthDateStr == null || birthDateStr.trim().equals("")) {
            model.setBirthDate(null);
            view.syncBirthDate();
        } else {
            try {
                var formatter = DateTimeFormatter.ofPattern(view.dateFormat);
                var birthDate = LocalDate.parse(birthDateStr, formatter);

                var errorList = new ArrayList<String>();
                if (model.isValidBirthdate(birthDate, errorList)) {
                    model.setBirthDate(birthDate);
                    view.syncAgeCategory();
                } else {
                    showError(errorList);
                    view.syncBirthDate();
                }
            } catch (DateTimeParseException exception) {
                var errorList = new ArrayList<String>();
                errorList.add("Birth date must be in the " + view.dateFormat.toLowerCase() + " format.");
                this.showError(errorList);

                view.syncBirthDate();
            }
        }
    }

    private void showError(List<String> errorList) {
        var msg = new StringBuilder();
        if (errorList.isEmpty()) {
            msg = msg.append("No message to display.");
        } else {
            for (String s : errorList) {
                msg.append(s).append("\n");
            }
        }

        var messageLabel = new Label(msg.toString());

        var okButton = new Button("OK");
        var root = new VBox(new StackPane(messageLabel), new StackPane(okButton));
        root.setSpacing(10);

        var scene = new Scene(root);

        var stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.initOwner(view.getScene().getWindow());

        okButton.setOnAction(event -> stage.close());

        stage.setTitle("Error");
        stage.sizeToScene();
        stage.showAndWait();
    }

}

