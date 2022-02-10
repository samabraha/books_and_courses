package com.develogica.chapter_03;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

public class BindingListReference {
    public static void main(String[] args) {
        ListProperty<String> listProp1 = new SimpleListProperty<>(FXCollections.observableArrayList());
        ListProperty<String> listProp2 = new SimpleListProperty<>(FXCollections.observableArrayList());

        listProp1.bindBidirectional(listProp2);

        print("Before addAll()", listProp1, listProp2);
        listProp1.addAll("One", "Two");
        print("After addAll()", listProp1, listProp2);

        listProp2.set(FXCollections.observableArrayList("1", "2"));
        print("After listProp2.set()", listProp1, listProp2);

        listProp1.set(FXCollections.observableArrayList("1", "2"));

        listProp1.unbindContent(listProp2);
        print("After listProp1.unbind()", listProp1, listProp2);

        listProp1.set(FXCollections.observableArrayList("D", "E", "F"));
        print("Unbound listProp1", listProp1, listProp2);

        listProp1.bindBidirectional(listProp2);
        print("After bindBidirectional()", listProp1, listProp2);

        listProp1.set(FXCollections.observableArrayList("A", "B", "C"));
        print("After set()", listProp1, listProp2);
    }

    public static void print(String message, ListProperty<String> listProp1, ListProperty<String> listProp2) {
        System.out.println(message);
        System.out.printf("listProp1.get(): %s, listProp2.get(): %s, ", listProp1.get(), listProp2.get());
        System.out.printf("listProp1.get() == listProp2.get(): %s%n", (listProp1.get() == listProp2.get()));
        System.out.println("-".repeat(30));
    }
}
