package com.develogica.chapter_02;

import javafx.beans.property.*;

public class Book {
    private final StringProperty title =
            new SimpleStringProperty(this, "title", "Unknown");
    private final DoubleProperty price = new SimpleDoubleProperty(this, "price", 0.0);
    private final ReadOnlyStringWrapper ISBN =
            new ReadOnlyStringWrapper(this, "ISBN", "Unknown");

    public final String getISBN() {
        return ISBN.get();
    }

    public final ReadOnlyStringWrapper ISBNProperty() {
        return ISBN;
    }

    public final void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public final String getTitle() {
        return title.get();
    }

    public final StringProperty titleProperty() {
        return title;
    }



    public void setTitle(String title) {
        this.title.set(title);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public Book(String title, double price,String ISBN) {
        this.title.set(title);
        this.price.set(price);
        this.ISBN.set(ISBN);
    }
}

class BookPropertyTest {
    public static void main(String[] args) {
        Book myBook = new Book("The Eternal Sunshine of a Spotless Mind",
                20.0, String.valueOf((int) (Math.random() * Integer.MAX_VALUE)));

        System.out.println("After creating the book object: ");
        printDetails(myBook.titleProperty());
        printDetails(myBook.priceProperty());
        printDetails(myBook.ISBNProperty());

        myBook.setTitle("Core Java v12");
        myBook.setPrice(39.99);

        System.out.println("\nAfter changing the book properties: ");
        printDetails(myBook.titleProperty());
        printDetails(myBook.priceProperty());
        printDetails(myBook.ISBNProperty());
    }

    private static void printDetails(ReadOnlyProperty<?> property) {
        var name = property.getName();
        var value = property.getValue();
        var bean = property.getBean();

        var beanClassName = bean == null ? "null" : bean.getClass().getSimpleName();
        String propClassName = property.getClass().getSimpleName();

        System.out.printf("%s [Name:%s, Bean:%s, Value:%s]%n",
                propClassName, name, beanClassName, value);
    }
}