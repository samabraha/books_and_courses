package com.develogica.chapter_02;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;


public class BidirectionalBinding {
    public static void main(String[] args) {
        IntegerProperty x = new SimpleIntegerProperty(1);
        IntegerProperty y = new SimpleIntegerProperty(2);
        IntegerProperty z = new SimpleIntegerProperty(3);

        var propUtil = PropUtil.getInstance();

        String message = "Before binding:";
        propUtil.printProps(message, x, y, z);

        x.bindBidirectional(y);
        message = "After binding-1:";
        propUtil.printProps(message, x, y, z);

        propUtil.namePrefix("var")
                .beforeList("{")
                .beforeItem("\n\t-> ")
                .afterList("\n}")
                .keyValueSeparator("], [")
                .toggleAppendAfterLastItem();

        x.bindBidirectional(z);
        message = "After binding-2:";
        propUtil.printProps(message, x, y, z);

        z.set(19);
        message = "After changing z:";
        propUtil.printProps(message, x, y, z);

        x.unbindBidirectional(y);
        x.unbindBidirectional(z);
        x.set(100);
        y.set(200);
        z.set(300);

        message = "After unbinding and changing them separately:";
        propUtil.printProps(message, x, y, z);
    }
}

