package com.develogica.chapter_03;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class ListBindingDemo {
    public static void main(String[] args) {
        ListProperty<String> listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        StringProperty initString = new SimpleStringProperty("Size: ");
        StringProperty descr = new SimpleStringProperty();
        descr.bind(initString.concat(listProperty.sizeProperty())
                .concat(", Empty: ")
                .concat(listProperty.emptyProperty())
                .concat(", List: ")
                .concat(listProperty.asString()));
        System.out.println("Before addAll(): " + descr.get());
        listProperty.addAll("Adam", "Eve");
        System.out.println("Before addAll(): " + descr.get());
    }
}

class BindingToListElements {
    public static void main(String[] args) {
        ListProperty<String> listProp = new SimpleListProperty<>(FXCollections.observableArrayList());

        ObjectBinding<String> binding = listProp.valueAt(listProp.sizeProperty().subtract(1));

        listProp.add("Anna");
        System.out.println("After listProp.add(\"Anna\"):");
        System.out.printf("List: %s, Last item: %s%n%n", listProp.get(), binding.get());

        listProp.addAll("Ben", "Carla");
        System.out.println("After listProp.addAll(\"Ben\", \"Carla\"):");
        System.out.printf("List: %s, Last item: %s%n%n", listProp.get(), binding.get());

        listProp.remove("Ben");
        System.out.println("After listProp.remove(\"Ben\"):");
        System.out.printf("List: %s, Last item: %s%n%n", listProp.get(), binding.get());

        listProp.remove("Carla");
        System.out.println("After listProp.remove(\"Carla\"):");
        System.out.printf("List: %s, Last item: %s%n%n", listProp.get(), binding.get());

        listProp.clear();
        System.out.println("After listProp.clear():");
        System.out.printf("List: %s, Last item: %s%n%n", listProp.get(), binding.get());
    }
}