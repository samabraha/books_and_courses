package com.develogica.chapter_11.mvc.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Person {

    public enum AgeCategory {
        BABY, CHILD, TEEN, ADULT, SENIOR, UNKNOWN
    }

    private final ReadOnlyIntegerWrapper personId
            = new ReadOnlyIntegerWrapper(this, "personId", personSequence.incrementAndGet());
    private final StringProperty firstName
            = new SimpleStringProperty(this, "firstName", null);
    private final StringProperty lastName
            = new SimpleStringProperty(this, "lastName", null);
    private final ObjectProperty<LocalDate> birthDate
            = new SimpleObjectProperty<>(this, "birthDate", null);

    private static AtomicInteger personSequence = new AtomicInteger(0);

    public Person() {
        this(null, null, null);
    }

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.birthDate.set(birthDate);
    }

    public int getPersonId() {
        return personId.get();
    }

    public ReadOnlyIntegerProperty personIdProperty() {
        return personId.getReadOnlyProperty();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public static AtomicInteger getPersonSequence() {
        return personSequence;
    }

    public static void setPersonSequence(AtomicInteger personSequence) {
        Person.personSequence = personSequence;
    }

    public boolean isValidBirthdate(LocalDate bDate) {
        return isValidBirthdate(bDate, new ArrayList<>());

    }

    public boolean isValidBirthdate(LocalDate birthDate, List<String> errorList) {
        if (birthDate == null) {
            return true;
        }

        if (birthDate.isAfter(LocalDate.now())) {
            errorList.add("Birth date must not be in the future.");
            return false;
        }

        return true;
    }

    public boolean isValidPerson(List<String> errorList) {
        return isValidPerson(this, errorList);
    }

    private boolean isValidPerson(Person person, List<String> errorList) {
        boolean isValid = true;

        var firstName = person.getFirstName();
        if (firstName == null || firstName.trim().length() == 0) {
            errorList.add("First name must contain at least one character.");
            isValid = false;
        }

        var lastName = person.getLastName();
        if (lastName == null || lastName.trim().length() == 0) {
            errorList.add("Last name must contain at least one character.");
            isValid = false;
        }

        if (!isValidBirthdate(this.birthDate.get(), errorList)) {
            isValid = false;
        }

        return isValid;
    }

    public AgeCategory getAgeCategory() {
        if (birthDate.get() == null) {
            return AgeCategory.UNKNOWN;
        }

        long years = ChronoUnit.YEARS.between(birthDate.get(), LocalDate.now());
        if (years >= 0 && years < 2) {
            return AgeCategory.BABY;
        } else if (years >= 2 && years < 13) {
            return AgeCategory.CHILD;
        } else if (years >= 13 && years <= 19) {
            return AgeCategory.TEEN;
        } else if (years > 19 && years <= 50) {
            return AgeCategory.ADULT;
        } else if (years > 50) {
            return AgeCategory.SENIOR;
        } else {
            return AgeCategory.UNKNOWN;
        }
    }

    public boolean save(List<String> errorList) {
        boolean isSaved = false;

        if (isValidPerson(errorList)) {
            System.out.println("Saved " + this);
            isSaved = true;
        }

        return isSaved;
    }

    @Override
    public String toString() {
        return "[personId=%s, firstName=%s, lastName=%s, birthDate=%s]"
                .formatted(personId.get(), firstName.get(), lastName.get(), birthDate.get());
    }
}

