package com.develogica.chapter_03;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class SimpleListChangeDemo {
    private static ObservableList<String> list = FXCollections.observableArrayList("one", "two", "three", "four", "five");
    public static void main(String[] args) {
        list.addListener(SimpleListChangeDemo::otherChanged);

        System.out.println(list);

        list.add("six");

        list.addAll("seven", "eight");
        FXCollections.sort(list);
        list.add("nine");
        System.out.println(list);

        list.clear();
    }

    public static void otherChanged(ListChangeListener.Change<? extends String> change) {
        while (change.next()) {
            if (change.wasPermutated()){
                System.out.println("Permuted");
            } else if(change.wasUpdated()) {
                System.out.println("Updated");
            } else if(change.wasReplaced()) {
                System.out.println("Replaced");
            } else  {
                if (change.wasRemoved()) {
                    System.out.println("Removed");
                } else if (change.wasAdded()) {
                    System.out.println("Added");
                }
            }
        }
    }

    public static void onChanged(ListChangeListener.Change<? extends String> change) {
        System.out.println("List has changed ");

        change.next();

        if (change.wasAdded()) {
            System.out.printf("\tAdded %d item(s)%n", change.getAddedSize());
        }
        if (change.wasPermutated()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("Old: %d : %s,\t\t New:%d : %s%n",
                        i, list.get(change.getPermutation(i)), change.getPermutation(i), list.get(i));
            }
        }
    }
}

