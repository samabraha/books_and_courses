package com.develogica.chapter_13;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class PersonTableUtil {

    public static ObservableList<Person> getPersonList() {
        Person person1 = new Person("Abraham", "Garibaldi", LocalDate.of(1984, 6, 1));
        Person person2 = new Person("Belmont", "Ferdinand", LocalDate.of(1956, 7, 6));
        Person person3 = new Person("Carly", "Edgar", LocalDate.of(1996, 2, 13));
        Person person4 = new Person("Daniel", "Doyle", LocalDate.of(1975, 4, 26));
        Person person5 = new Person("Elisa", "Crawford", LocalDate.of(2018, 11, 5));
        Person person6 = new Person("Frederick", "Benson", LocalDate.of(1989, 3, 11));
        Person person7 = new Person("Gibbon", "Andrew", LocalDate.of(2008, 7, 18));

        return FXCollections.observableArrayList(person1, person2, person3, person4, person5, person6, person7);
    }

    public static TableColumn<Person, Integer> getIdColumn() {
        var personIdColumn = new TableColumn<Person, Integer>("ID");
        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        return personIdColumn;
    }

    public static TableColumn<Person, String> getFirstNameColumn() {
        var firstNameColumn = new TableColumn<Person, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return firstNameColumn;
    }

    public static TableColumn<Person, String> getLastNameColumn() {
        var lastNameColumn = new TableColumn<Person, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lastNameColumn;
    }


    public static TableColumn<Person, LocalDate> getBirthDateColumn() {
        var birthDateColumn = new TableColumn<Person, LocalDate>("Birth Date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        return birthDateColumn;
    }

    public static TableColumn<Person, Person.AgeCategory> getAgeCategory() {
        var ageCategoryColumn = new TableColumn<Person, Person.AgeCategory>("Age Category");
        ageCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));
        return ageCategoryColumn;
    }

    public static TableColumn<Person, String> getAgeColumn() {
        var ageColumn = new TableColumn<Person, String>("Age");
        ageColumn.setCellValueFactory(cellData -> {
            var person = cellData.getValue();
            var dateOfBirth = person.getBirthDate();
            var ageInYears = "Unknown";
            if (dateOfBirth != null) {
                var years = YEARS.between(dateOfBirth, LocalDate.now());
                if (years == 0) {
                    ageInYears = "< 1 year";
                } else if (years == 1) {
                    ageInYears = "1 year";
                } else {
                    ageInYears = years + " years";
                }
            }
            return new ReadOnlyStringWrapper(ageInYears);
        });
        return ageColumn;
    }
}

