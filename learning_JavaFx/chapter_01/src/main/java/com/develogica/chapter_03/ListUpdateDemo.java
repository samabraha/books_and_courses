package com.develogica.chapter_03;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.util.Callback;

public class ListUpdateDemo {
    public static void main(String[] args) {
        Callback<IntegerProperty, Observable[]> extractor = (IntegerProperty p) -> {
            System.out.println("Extractor called for " + p);
            return new Observable[]{p};
        };

        var list = FXCollections.observableArrayList(extractor);

        System.out.println("Before adding elements");
        IntegerProperty p1 = new SimpleIntegerProperty(10);
        IntegerProperty p2 = new SimpleIntegerProperty(20);
        list.addAll(p1, p2);
        System.out.println("After adding elements");
        list.addListener(ListUpdateDemo::onChanged);

        p1.set(100);
    }

    private static void onChanged(ListChangeListener.Change<? extends IntegerProperty> change) {
        while (change.next()) {
            if (change.wasUpdated()) {
                System.out.println("An update is detected");
                int start = change.getFrom();
                int end = change.getTo();
                System.out.printf("Updated range [%d, %d]%n", start, end);
                var updatedElementsList = change.getList().subList(start, end);
                System.out.println("updatedElementsList = " + updatedElementsList);
            }
        }
    }
}

