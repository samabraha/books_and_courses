package com.develogica.chapter_03;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ListPropertyDemo {
    public static void main(String[] args) {
        ListProperty<String> lp = new SimpleListProperty<>(FXCollections.observableArrayList());

        lp.addListener(ListPropertyDemo::invalidated);
        lp.addListener(ListPropertyDemo::changed);
        lp.addListener(ListPropertyDemo::onChanged);


        System.out.println("Before addAll()");
        lp.addAll("One", "Two", "Three");
        System.out.println("After addAll()");

        System.out.println("Before set()");
        lp.set(FXCollections.observableArrayList("Four", "Five", "Six"));
        System.out.println("After set()");

        System.out.println("Before remove()");
        lp.remove("Five");
        System.out.println("After remove()");
    }

    public static void invalidated(Observable observable) {
        System.out.println("List property is invalid");
    }

    public static void changed(
            ObservableValue<? extends ObservableList<String>> observable,
            ObservableList<String> oldList, ObservableList<String> newList) {

        System.out.println("List Property has changed: ");
        System.out.println("oldList = " + oldList);
        System.out.println("newList = " + newList);
    }

    public static void onChanged(ListChangeListener.Change<? extends String> change) {
        while (change.next()) {
            String action;
            if (change.wasPermutated()) action = "Permutated";
            else {
                if (change.wasUpdated()) action = "Updated";
                else {
                    if (change.wasRemoved() && change.wasAdded()) action = "Replaced";
                    else {
                        if (change.wasRemoved()) action = "Removed";
                        else action = "Added";
                    }
                }
            }

            System.out.println("Action taken on list: " + action);
            System.out.printf("Removed %s%n", change.getRemoved());
            System.out.println("Added: " + change.getAddedSubList());
        }
    }
}
