package com.develogica.chapter_03;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.util.Callback;

public class ListChangeTest {
    public static void main(String[] args) {
        Callback<Person, Observable[]> callback
                = (Person p) -> new Observable[] { p.firstNameProperty(), p.lastNameProperty() };

        var list = FXCollections.observableArrayList(callback);

        list.addListener(new PersonListChangeListener());

        Person person1 = new Person("Grumpy", "Earth");
        System.out.printf("Before adding %s to list: %s%n", person1, list);
        list.add(person1);
        System.out.printf("After adding %s to list: %s%n", person1, list);

        Person person2 = new Person("Dopey", "Air");
        Person person3 = new Person("Doc", "Fire");
        System.out.printf("Before adding %s and %s to list: %s%n", person2, person3, list);
        list.addAll(person2, person3);
        System.out.printf("After adding %s and %s to list: %s%n", person2, person3, list);

        System.out.println("Before sorting list: "+ list);
        FXCollections.sort(list);
        System.out.println("After sorting list: "+ list);


        System.out.printf("Before updating %s on list: %s%n", person1, list);
        person1.setLastName("Smith");
        System.out.printf("After updating %s on list: %s%n", person1, list);

        Person person = list.get(0);
        Person person4 = new Person("Happy", "Water");
        System.out.printf("Before replacing %s with %s on list: %s%n", person, person4, list);
        list.set(0, person4);
        System.out.printf("After replacing %s with %s on list: %s%n", person, person4, list);

        System.out.println("Before setAll(): " + list);
        Person person5 = new Person("Bashful", "Light");
        Person person6 = new Person("Sneezy", "Shadow");
        Person person7 = new Person("Sleepy", "Magic");
        list.setAll(person5, person6, person7);
        System.out.println("After setAll(): " + list);

        System.out.println("Before removeAll(): " + list);
        list.removeAll(person5, person7);
        System.out.println("Before removeAll(): " + list);


    }
}

