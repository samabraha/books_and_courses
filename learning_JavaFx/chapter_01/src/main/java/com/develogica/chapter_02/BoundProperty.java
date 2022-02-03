package com.develogica.chapter_02;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BoundProperty {
    public static void main(String[] args) {
        IntegerProperty x = new SimpleIntegerProperty(10);
        IntegerProperty y = new SimpleIntegerProperty(20);
        IntegerProperty z = new SimpleIntegerProperty(30);

        z.bind(x.add(y));

        System.out.printf("After initial binding x=%d, y=%d, z.isBound=%s, z=%d %n", x.get(), y.get(), z.isBound(), z.get());

        x.set(15);
        y.set(19);
        System.out.printf("After update x and y, x=%d, y=%d, z.isBound=%s, z=%d %n", x.get(), y.get(), z.isBound(), z.get());

        z.unbind();
        x.set(100);
        y.set(200);
        System.out.printf("After unbinding x=%d, y=%d, z.isBound=%s, z=%d %n", x.get(), y.get(), z.isBound(), z.get());
    }
}

