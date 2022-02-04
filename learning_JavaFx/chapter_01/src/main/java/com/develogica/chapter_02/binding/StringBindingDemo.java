/** Who gets to document a package? */
package com.develogica.chapter_02.binding;

import com.develogica.chapter_02.Book;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

public class StringBindingDemo {
    public static void main(String[] args) {
        DoubleProperty radius = new SimpleDoubleProperty(7.0);
        DoubleProperty area = new SimpleDoubleProperty(0);



        StringProperty initString = new SimpleStringProperty("Radius = ");

        area.bind(radius.multiply(radius).multiply(Math.PI));

        StringExpression expression =
                initString.concat(radius.asString())
                        .concat(", Area = ")
                        .concat(area.asString("%.2f"));

        System.out.println(expression.getValue());
        radius.setValue(14.0);
        System.out.println(expression.getValue());

    }
}

class BindingDemos {
    public static void main(String[] args) {
//        demoObjectBinding();
        int x = 1, y = 2, z = 3;
//        demoBooleanBinding(x, y, z);
        demoTernary(y);
    }

    private static void demoTernary(int x) {
        IntegerProperty num = new SimpleIntegerProperty(x);

        StringBinding descr = new When(num.divide(2).multiply(2).isEqualTo(num))
                .then("even")
                .otherwise("odd");

        System.out.printf("num: %d descr: %s%n", num.get(), descr.get());
        num.set(19);
        System.out.printf("num: %d descr: %s%n", num.get(), descr.get());
    }

    private static void demoBooleanBinding(int a, int b, int c) {
        IntegerProperty x = new SimpleIntegerProperty(a);
        IntegerProperty y = new SimpleIntegerProperty(b);
        IntegerProperty z = new SimpleIntegerProperty(c);

        var condition = x.greaterThan(y).and(y.isNotEqualTo(z));

        System.out.println("condition.get() = " + condition.get());
        x.set(3);
        System.out.println("condition.get() = " + condition.get());
    }

    private static void demoObjectBinding() {
        Book book1 = new Book("J1", 25, "31648541");
        Book book2 = new Book("J2", 29, "37154851");

        ObjectProperty<Book> property1 = new SimpleObjectProperty<>(book1);
        ObjectProperty<Book> property2 = new SimpleObjectProperty<>(book2);

        BooleanBinding isEqual = property1.isEqualTo(property2);

        System.out.println(isEqual.get());
        property2.set(book1);
        System.out.println(isEqual.get());
    }
}