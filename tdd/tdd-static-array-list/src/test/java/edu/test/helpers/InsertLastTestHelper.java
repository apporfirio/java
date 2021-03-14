package edu.test.helpers;

import edu.test.models.InsertLastTestCase;

public class InsertLastTestHelper {

    public static InsertLastTestCase[] getTestCases() {
        Object[][] inputs = getInputs();
        InsertLastTestCase[] testCases = new InsertLastTestCase[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            InsertLastTestCase testCase = new InsertLastTestCase();

            testCase.setGivenCapacity(inputs[i].length);
            testCase.setGivenItems(inputs[i]);
            testCase.setExpectedItems(inputs[i]);

            testCases[i] = testCase;
        }

        return testCases;
    }

    private static Object[][] getInputs() {
        return new Object[][] {
                new Object[] { true },
                new Object[] { "1.99f", "-3.14159f" },
                new Object[] { null, new Object(), new Object(), new Object[100], null }
        };
    }
}
