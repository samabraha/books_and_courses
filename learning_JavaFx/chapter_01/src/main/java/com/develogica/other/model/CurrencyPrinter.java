package com.develogica.other.model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyPrinter {
    public static void main(String[] args) {
        double amount = 12345678.3344;
        NumberFormat format = NumberFormat.getCurrencyInstance();
        System.out.println(format.format(amount));

        format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        System.out.println(format.format(amount));
    }
}
