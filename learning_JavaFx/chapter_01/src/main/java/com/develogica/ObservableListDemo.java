package com.develogica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObservableListDemo {
    public static void main(String[] args) {
        ObservableList<String> list = FXCollections.observableArrayList("one", "two");
        System.out.println("After creating list " + list);

        list.addAll("three", "four");
        System.out.println("After adding elements " + list);

        list.remove(1, 3);
        System.out.println("After removing elements " + list);

        list.retainAll("one");

        System.out.println("After retaining \"one\": " + list);
        ObservableList<String> list2 =
                FXCollections.observableArrayList("1", "2", "3");

        list.setAll(list2);
        System.out.println("After setting list2 to list: " + list2);

        ObservableList<String> list3 =
                FXCollections.observableArrayList("ten", "twenty", "thirty");

        ObservableList<String> list4 =
                FXCollections.concat(list2, list3);

        System.out.println("list2 = " + list2);
        System.out.println("list3 = " + list3);
        System.out.println("after concat() list4 = " + list4);

    }
}

