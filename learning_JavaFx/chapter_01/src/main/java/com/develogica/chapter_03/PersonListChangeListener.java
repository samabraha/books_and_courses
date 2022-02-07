package com.develogica.chapter_03;

import javafx.collections.ListChangeListener;

public class PersonListChangeListener implements ListChangeListener<Person> {

    @Override
    public void onChanged(Change<? extends Person> change) {
        while (change.next()) {
            if (change.wasPermutated()) {
                handlePermutated(change);
            } else if (change.wasUpdated()) {
                handleUpdated(change);
            } else if (change.wasReplaced()) {
                handleReplaced(change);
            } else {
                if (change.wasRemoved()) {
                    handleRemoved(change);
                } else if (change.wasAdded()) {
                    handleAdded(change);
                }
            }
        }
    }

    public void handlePermutated(Change<? extends Person> change) {
        System.out.println("Change type: Permutated");
        System.out.println("Change range: " + getRangeText(change));
        int start = change.getFrom();
        int end = change.getTo();
        for (int oldIndex = start; oldIndex < end; oldIndex++) {
            int newIndex = change.getPermutation(oldIndex);
            System.out.printf("index[%d] moved to index[%d]%n", oldIndex, newIndex);
        }
    }

    public void handleUpdated(Change<? extends Person> change) {
        System.out.println("Change type: Updated");
        System.out.println("Change range: " + getRangeText(change));
        System.out.println("Updated elements are: " +
                change.getList().subList(change.getFrom(), change.getTo()));
    }

    public void handleReplaced(Change<? extends Person> change) {
        System.out.println("Change type: Replaced");

        handleRemoved(change);
        handleAdded(change);
    }

    public void handleRemoved(Change<? extends Person> change) {
        System.out.println("Change type: Removed");

        int removedSize = change.getRemovedSize();
        var subList = change.getRemoved();

        System.out.println("Removed size: " + removedSize);
        System.out.println("Removed range: " + getRangeText(change));
        System.out.println("Removed list: " + subList);
    }

    public void handleAdded(Change<? extends Person> change) {
        System.out.println("Change type: Added");

        int addedSize = change.getAddedSize();
        var addedSubList = change.getAddedSubList();

        System.out.println("Added size: " + addedSize);
        System.out.println("Added range: " + getRangeText(change));
        System.out.println("Added list: " + addedSubList);
    }

    public String getRangeText(Change<? extends Person> change) {
        return String.format("[%s, %s]", change.getFrom(), change.getTo());
    }
}
