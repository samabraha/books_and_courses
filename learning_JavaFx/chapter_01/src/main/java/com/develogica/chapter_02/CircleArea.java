package com.develogica.chapter_02;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CircleArea {
    public static void main(String[] args) {
        double radVal = 7.0;
        DoubleProperty radius = new SimpleDoubleProperty(radVal);
        DoubleBinding area = radius.multiply(radius).multiply(Math.PI);

        System.out.println("area.get() = " + area.get());

        radVal *= 2;
        System.out.println("radVal = " + radVal);
        radius.setValue(radVal);
        System.out.println("area.get() = " + area.get());

        DoubleProperty area2 = new SimpleDoubleProperty();
        area2.bind(radius.multiply(radius).multiply(Math.PI));

        System.out.println("area2.get() = " + area2.get());
    }
}
