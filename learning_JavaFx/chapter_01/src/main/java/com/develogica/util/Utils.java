package com.develogica.util;

public class Utils {
    public static boolean betweenInclusive(int number, int a, int b) {
        var smaller = Math.min(a, b);
        var bigger = Math.max(a, b);
        return number >= smaller && number <= bigger;
    }

    public static boolean between(int number, int a, int b) {
        var smaller = Math.min(a, b);
        var bigger = Math.max(a, b);
        return number > smaller && number < bigger;
    }
}
