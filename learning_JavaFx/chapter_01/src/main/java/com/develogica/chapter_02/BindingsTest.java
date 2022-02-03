package com.develogica.chapter_02;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BindingsTest {
    public static void main(String[] args) {
        var x = new SimpleIntegerProperty(100);
        var y = new SimpleIntegerProperty(200);
        var sum = x.add(y);

        System.out.println("After creating 'sum'");
        System.out.println("\tsum.isValid() = " + sum.isValid());

        int value = sum.intValue();

        System.out.println("After requesting value:");
        System.out.println("\tsum.isValid() = " + sum.isValid());
        System.out.println("value = " + value);

        x.set(250);
        System.out.println("After changing x to: " + x.intValue());
        System.out.println("sum.isValid() = " + sum.isValid());

        value = sum.intValue();
        System.out.println("After requesting value:");
        System.out.println("\tsum.isValid() = " + sum.isValid());
        System.out.println("value = " + value);

    }
}

