package edu.test.utils;

public class TestUtils {

    public static Object[] reverse(Object[] items) {
        Object[] reversed = new Object[items.length];

        for (int i = 0; i < items.length; i++) {
            reversed[items.length - 1 - i] = items[i];
        }

        return reversed;
    }
}
