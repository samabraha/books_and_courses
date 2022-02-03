package com.develogica.chapter_02;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BidirectionalBinding {
    public static void main(String[] args) {
        IntegerProperty x = new SimpleIntegerProperty(1);
        IntegerProperty y = new SimpleIntegerProperty(2);
        IntegerProperty z = new SimpleIntegerProperty(3);

        String message = "Before binding:";
        printProps(message, x, y, z);

        x.bindBidirectional(y);
        message = "After binding-1:";
        printProps(message, x, y, z);

        x.bindBidirectional(z);
        message = "After binding-2:";
        printProps(message, x, y, z);

        z.set(19);
        message = "After changing z:";
        printProps(message, x, y, z);

        x.unbindBidirectional(y);
        x.unbindBidirectional(z);
        x.set(100);
        y.set(200);
        z.set(300);

        message = "After unbinding and changing them separately:";
        printProps(message, x, y, z);
    }

    private static void printProps(String message, IntegerProperty x, IntegerProperty y, IntegerProperty z) {
        System.out.println(message);
        System.out.printf("\t[x:%d, y:%d, z:%d]%n", x.get(), y.get(), z.get());
        System.out.println();
    }
}
