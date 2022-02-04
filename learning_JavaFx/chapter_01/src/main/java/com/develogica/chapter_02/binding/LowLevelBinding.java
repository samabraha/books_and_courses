package com.develogica.chapter_02.binding;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Formatter;
import java.util.Locale;

public class LowLevelBinding {
    public static void main(String[] args) {
        final DoubleProperty radius = new SimpleDoubleProperty(7.0);
        final DoubleProperty area = new SimpleDoubleProperty(0);

        DoubleBinding areaBinding = new DoubleBinding() {
            {
                this.bind(radius);
            }

            @Override
            protected double computeValue() {
                double r = radius.get();
                double area = Math.PI * r * r;
                return area;
            }
        };

        area.bind(areaBinding);

        StringBinding descr = new StringBinding() {
            {
                this.bind(radius, area);
            }

            @Override
            protected String computeValue() {
                Formatter f = new Formatter();
                f.format(Locale.US, "Radius = %.2f, Area = %.2f", radius.get(), area.get());
                return f.toString();
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(radius, area));

            }

            @Override
            public void dispose() {
                System.out.println("Description binding is disposed");
            }

            @Override
            protected void onInvalidating() {
                System.out.println("Description is invalid");
            }
        };

        System.out.println(descr.getValue());
        radius.setValue(15);
        System.out.println(descr.getValue());
    }
}
