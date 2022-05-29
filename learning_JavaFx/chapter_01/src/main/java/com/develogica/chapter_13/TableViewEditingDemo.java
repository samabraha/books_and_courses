package com.develogica.chapter_13;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class TableViewEditingDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){

        var table = new TableView<>(PersonTableUtil.getPersonList());
        table.setEditable(true);

            addIdColumn(table);
            addFirstNameColumn(table);
            addLastNameColumn(table);
            addBirthDateColumn(table);
            addBabyColumn(table);
            addGenderColumn(table);

        var root = new HBox(table);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("TableView Editing Demo");
        stage.show();
    }

    private void addIdColumn(TableView<Person> table) {
        table.getColumns().add(PersonTableUtil.getIdColumn());
    }

    private void addFirstNameColumn(TableView<Person> table) {
        var firstNameColumn = PersonTableUtil.getFirstNameColumn();
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        table.getColumns().add(firstNameColumn);

    }

    private void addLastNameColumn(TableView<Person> table) {
        var lastNameColumn = PersonTableUtil.getLastNameColumn();
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        table.getColumns().add(lastNameColumn);

    }

    private void addBirthDateColumn(TableView<Person> table) {
        var birthDateColumn = PersonTableUtil.getBirthDateColumn();
        var converter = new LocalDateStringConverter();
        birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        table.getColumns().add(birthDateColumn);

    }

    private void addBabyColumn(TableView<Person> table) {
        var babyColumn = new TableColumn<Person, Boolean>("Baby?");
        babyColumn.setEditable(false);

        babyColumn.setCellValueFactory(cellData -> {
            Person person = cellData.getValue();
            var value = person.getAgeCategory() == Person.AgeCategory.BABY;
            return new ReadOnlyBooleanWrapper(value);
        });
        babyColumn.setCellFactory(CheckBoxTableCell.forTableColumn(babyColumn));

        table.getColumns().add(babyColumn);

    }

    private void addGenderColumn(TableView<Person> table) {
        var genderColumn = new TableColumn<Person, String>("Gender");
        genderColumn.setMinWidth(80);
        genderColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(null));
        genderColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Male", "Female"));
        genderColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            var person = event.getRowValue();
            System.out.printf("Gender changed for %s %s at row %d to %s.%n",
                    person.getFirstName(), person.getLastName(), row + 1, event.getNewValue());
        });

        table.getColumns().add(genderColumn);

    }
}
