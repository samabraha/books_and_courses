package com.develogica.chapter_02;

import javafx.beans.property.Property;

import java.util.Arrays;

public class PropUtil {
    private static PropUtil instance = null;

    private String propNamePrefix = "prop";
    private char letter = (char) 65;

    private String beforeList = "{";
    private String afterList = "}";

    private String keyValueSeparator = ":";
    private String beforeItem = "[";
    private String itemSeparator = ", ";
    private String afterItem = "]";

    public PropUtil itemSeparator(String itemSeparator) {
        this.itemSeparator = itemSeparator;
        return this;
    }


    private boolean appendAfterLastItem = false;

    private PropUtil() {

    }

    public static PropUtil getInstance() {
        if (instance == null) {
            instance = new PropUtil();
        }
        return instance;
    }


    public PropUtil beforeList(String opener) {
        this.beforeList = opener;
        return this;
    }

    public PropUtil afterList(String closer) {
        this.afterList = closer;
        return this;
    }

    public PropUtil namePrefix(String namePrefix) {
        this.propNamePrefix = namePrefix;
        return this;
    }

    public PropUtil keyValueSeparator(String keyValueSeparator) {
        this.keyValueSeparator = keyValueSeparator;
        return this;
    }

    public PropUtil beforeItem(String beforeItem) {
        this.beforeItem = beforeItem;
        return this;
    }

    public PropUtil afterItem(String afterItem) {
        this.afterItem = afterItem;
        return this;
    }

    public PropUtil appendAfterLastItem(boolean allow) {
        this.appendAfterLastItem = allow;
        return this;
    }


    public PropUtil toggleAppendAfterLastItem() {
        this.appendAfterLastItem = !this.appendAfterLastItem;
        return this;
    }

    public void printProps(String message, Property<?>... properties) {
        System.out.println(message);
        StringBuilder builder = new StringBuilder();

        var propertyIterator = Arrays.stream(properties).iterator();
        char tmpLetter = letter;
        while (propertyIterator.hasNext()) {
            var property = propertyIterator.next();
            String propName = property.getName().equalsIgnoreCase("")
                    ? propNamePrefix + letter++ : property.getName();

            builder.append(beforeItem)
                    .append(propName)
                    .append(keyValueSeparator)
                    .append(property.getValue())
                    .append(afterItem);

            if (propertyIterator.hasNext()) {
                builder.append(itemSeparator);
            } else {
                if (appendAfterLastItem) {
                    builder.append(itemSeparator);
                }
            }

        }



        letter = tmpLetter;
        System.out.printf("%s%s%s%n", beforeList, builder, afterList);
        System.out.println();
    }
}
