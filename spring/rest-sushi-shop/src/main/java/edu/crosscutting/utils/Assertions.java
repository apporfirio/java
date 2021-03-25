package edu.crosscutting.utils;

public abstract class Assertions {

    public static void assertNotNull(Object argument, String argumentName) {
        if (argument == null)
            throw new IllegalArgumentException(argumentName + " cannot be null");
    }

}
