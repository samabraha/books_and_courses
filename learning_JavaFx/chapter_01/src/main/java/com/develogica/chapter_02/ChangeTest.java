package com.develogica.chapter_02;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ChangeTest {
    public static void main(String[] args) {
        IntegerProperty counter = new SimpleIntegerProperty(100);
        counter.addListener(ChangeTest::changed);
        System.out.println("\nBefore changing counter value #1");
        counter.set(101);
        System.out.println("After changing counter value #1");

        System.out.println("\nBefore changing counter value #2");
        counter.set(102);
        System.out.println("After changing counter value #2");

        System.out.println("\nBefore changing counter value #3");
        counter.set(102);
        System.out.println("After changing counter value #3");

        System.out.println("\nBefore changing counter value #4");
        counter.set(103);
        System.out.println("After changing counter value #4");


    }

    private static void changed(Observable observable, Number oldValue, Number newValue) {
        System.out.printf("Counter changed from %d to %d%n", oldValue, newValue);
    }
}

