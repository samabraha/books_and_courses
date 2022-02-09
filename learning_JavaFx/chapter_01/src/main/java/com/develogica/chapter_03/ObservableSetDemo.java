package com.develogica.chapter_03;


import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;

import java.util.HashSet;
import java.util.Set;

public class ObservableSetDemo {
    public static void main(String[] args) {
        var set1 = FXCollections.observableSet("one", "two", "three");
        System.out.println("set1 = " + set1);

        Set<String> set2 = new HashSet<>();
        set2.add("one");
        set2.add("two");
        System.out.println("set2 = " + set2);

        var set3 = FXCollections.observableSet(set2);
        set3.addListener(ObservableSetDemo::onChange);
        set3.add("three");
        System.out.println("set3 = " + set3);

    }

    private static void onChange(SetChangeListener.Change<? extends String> change) {
        if (change.wasAdded()) {
            System.out.println("Added: " + change.getElementAdded());
        }
    }
}

