package com.develogica.chapter_11.mvc.view;

import com.develogica.chapter_11.mvc.model.Person;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class PersonView extends GridPane {
    private final Person model;

    Label personIdLabel = new Label("Person ID:");
    Label firstNameLabel = new Label("First Name:");
    Label lastNameLabel = new Label("Last Name:");
    Label birthDateLabel = new Label("Birth Date:");
    Label ageCategoryLabel = new Label("Age Category:");

    TextField personIdTxtFld = new TextField();
    TextField firstNameTxtFld = new TextField();
    TextField lastNameTxtFld = new TextField();
    TextField birthDateTxtFld = new TextField();
    TextField ageCategoryTxtFld = new TextField();

    Button saveButton = new Button("_Save");
    Button closeButton = new Button("_Close");

    String dateFormat;

    public PersonView(Person model, String dateFormat) {
        this.model = model;
        this.dateFormat = dateFormat;

        layoutForm();
        initFieldDate();
        bindFieldsToModel();
    }

    private void initFieldDate() {
        syncBirthDate();
    }

    private void layoutForm() {
        setHgap(5);
        setVgap(5);

        add(personIdLabel, 1, 1);
        add(firstNameLabel, 1, 2);
        add(lastNameLabel, 1, 3);
        add(birthDateLabel, 1, 4);
        add(ageCategoryLabel, 1, 5);

        add(personIdTxtFld, 2, 1);
        add(firstNameTxtFld, 2, 2);
        add(lastNameTxtFld, 2, 3);
        add(birthDateTxtFld, 2, 4);
        add(ageCategoryTxtFld, 2, 5);

        var buttonBox = new VBox(saveButton, closeButton);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        closeButton.setMaxWidth(Double.MAX_VALUE);

        add(buttonBox, 3, 1, 1, 5);

        personIdTxtFld.setDisable(true);
        ageCategoryTxtFld.setDisable(true);

        birthDateTxtFld.setPromptText(dateFormat.toLowerCase());
    }

    private void bindFieldsToModel() {
        personIdTxtFld.textProperty().bind(model.personIdProperty().asString());
        firstNameTxtFld.textProperty().bindBidirectional(model.firstNameProperty());
        lastNameTxtFld.textProperty().bindBidirectional(model.lastNameProperty());
    }

    void syncBirthDate() {
        var birthDate = model.getBirthDate();

        if (birthDate != null) {
            birthDateTxtFld.setText(birthDate.format(DateTimeFormatter.ofPattern(dateFormat)));
        }

        syncAgeCategory();
    }

    void syncAgeCategory() {
        ageCategoryTxtFld.setText(model.getAgeCategory().toString());
    }
}
