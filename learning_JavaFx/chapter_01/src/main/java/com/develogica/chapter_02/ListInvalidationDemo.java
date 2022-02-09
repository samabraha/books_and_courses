package com.develogica.chapter_02;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListInvalidationDemo {
    public static void main(String[] args) {
        ObservableList<String> list = FXCollections.observableArrayList("one", "two");

        list.addListener(ListInvalidationDemo::invalidated);
        System.out.println("Before adding 'three'");

        list.add("three");
        System.out.println("After adding 'three'");

        System.out.println("Before adding 'four' and 'five'");
        list.addAll("four", "five");
        System.out.println("Before replacing 'one' with 'one'");
        list.set(0, "one");
        System.out.println("After replacing 'one' with 'one'");


    }

    private static void invalidated(Observable observable) {
        System.out.println("List is invalid");
    }
}
