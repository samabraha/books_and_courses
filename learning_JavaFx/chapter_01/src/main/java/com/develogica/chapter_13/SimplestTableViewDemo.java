package com.develogica.chapter_13;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class SimplestTableViewDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var table = getTable();



        var addButton = new Button("ADD");
        addButton.setOnAction(event -> {
            var sam = new Person("Samson", "Abraha", LocalDate.of(1986, 3, 19));
            table.getItems().add(sam);
        });

        var removeButton = new Button("REMOVE");

        var exitButton = new Button("EXIT");
        exitButton.setOnAction(event -> Platform.exit());

        var buttonBox = new HBox(addButton, removeButton, exitButton);
        buttonBox.setSpacing(10);

        var root = new VBox(table, buttonBox);

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SimplestTableView Demo");
        stage.show();
    }

    private TableView<Person> getTable() {
        var columns = List.<TableColumn<Person, ?>>of(
                PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                PersonTableUtil.getBirthDateColumn(),
                PersonTableUtil.getAgeColumn(),
                PersonTableUtil.getAgeCategory()
        );
        var table = new TableView<>( PersonTableUtil.getPersonList());

//        var callback = new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> cellData) {
//                Person person = cellData.getValue();
//                return person.firstNameProperty();
//            }
//        };
//
//

        table.placeholderProperty().bind(
                new When(new SimpleIntegerProperty(0).isEqualTo(table.getVisibleLeafColumns().size()))
                        .then(
                                new When(new SimpleIntegerProperty(0)
                                        .isEqualTo(table.getItems().size()))
                                        .then(new Label("No columns and data exist."))
                                        .otherwise(new Label("No columns exist.")))
                        .otherwise(
                                new When(new SimpleIntegerProperty(0)
                                        .isEqualTo(table.getItems().size()))
                                        .then(new Label("No data exist."))
                                        .otherwise((Label) null))
        );

        table.getColumns().addAll(columns);
        return table;
    }
}
