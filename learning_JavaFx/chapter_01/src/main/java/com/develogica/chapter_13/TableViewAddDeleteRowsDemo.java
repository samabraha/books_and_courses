package com.develogica.chapter_13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TableViewAddDeleteRowsDemo extends Application {
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final DatePicker birthDateField = new DatePicker();
    Button addButton = new Button("Add");
    Button deleteButton = new Button("Delete Selected Rows");
    TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var tvsm = table.getSelectionModel();
        tvsm.setSelectionMode(SelectionMode.MULTIPLE);
        tvsm.selectedIndexProperty().addListener(event
                -> deleteButton.setDisable(tvsm.getSelectedIndex() == -1));

        table.getColumns().addAll(List.<TableColumn<Person, ?>>of(
                PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                PersonTableUtil.getBirthDateColumn()
                )
        );

        GridPane newDataPane = getNewPersonDataPane();

        var restoreButton = new Button("Restore Rows");
        restoreButton.setOnAction(event -> restoreRows());

        var clearSelectionButton = new Button("Clear Selection");
        clearSelectionButton.setOnAction(event -> table.getSelectionModel().clearSelection());

        firstNameField.textProperty().addListener(listener -> resetAddButton());
        lastNameField.textProperty().addListener(listener -> resetAddButton());
        birthDateField.valueProperty().addListener(listener -> resetAddButton());

        deleteButton.setDisable(true);
        deleteButton.setOnAction(event -> deleteSelectedRows());

        resetAddButton();

        var root = new VBox(newDataPane, new HBox(restoreButton, deleteButton, clearSelectionButton), table);
        root.setSpacing(5);
        root.setPadding(new Insets(5));

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TableView Add/Delete Rows - Demo");
        stage.show();
    }

    private void resetAddButton() {
        var firstNameVal = firstNameField.getText();
        var lastNameVal = lastNameField.getText();
        var birthDateValue = birthDateField.getValue();

        var isValidFirstName = firstNameVal == null || firstNameVal.isBlank();
        var isValidLastName = lastNameVal == null || lastNameVal.isBlank();
        var isValidBirthDate = birthDateValue == null;

        addButton.setDisable(
                isValidFirstName || isValidLastName || isValidBirthDate);
    }

    private void deleteSelectedRows() {
        var tsvm = table.getSelectionModel();
        if (tsvm.isEmpty()) {
            System.out.println("Please, select a row to delete.");
            return;
        }

        var selectedIndexList = new ArrayList<>(tsvm.getSelectedIndices());
        selectedIndexList.sort(null);
        for (int i = selectedIndexList.size() - 1; i >= 0; i--) {
            tsvm.clearSelection(selectedIndexList.get(i));
            table.getItems().remove(selectedIndexList.get(i).intValue());

        }
    }

    private void restoreRows() {
        table.getItems().clear();
        table.getItems().addAll(PersonTableUtil.getPersonList());
    }

    private GridPane getNewPersonDataPane() {
        var pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);

        pane.addRow(0, new Label("First Name:"), firstNameField);
        pane.addRow(1, new Label("Last Name:"), lastNameField);
        pane.addRow(2, new Label("Birth Date:"), birthDateField);

        addButton.setOnAction(event -> addPerson());

        pane.add(addButton, 2, 0);

        return pane;
    }

    private void addPerson() {
        Person person = getPerson();
        table.getItems().add(person);
        clearFields();
    }

    private Person getPerson() {
        return new Person(
                firstNameField.getText(),
                lastNameField.getText(),
                birthDateField.getValue()
        );
    }

    private void clearFields() {
        firstNameField.setText(null);
        lastNameField.setText(null);
        birthDateField.setValue(null);
    }
}
