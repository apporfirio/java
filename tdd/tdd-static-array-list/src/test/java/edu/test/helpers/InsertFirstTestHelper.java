package edu.test.helpers;

import edu.test.utils.TestUtils;
import edu.test.models.InsertFirstTestCase;

public class InsertFirstTestHelper {

    public static InsertFirstTestCase[] getTestCases() {
        Object[][] inputs = getInputs();
        InsertFirstTestCase[] testCases = new InsertFirstTestCase[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            InsertFirstTestCase testCase = new InsertFirstTestCase();

            testCase.setGivenCapacity(inputs[i].length);
            testCase.setGivenItems(inputs[i]);
            testCase.setExpectedItems(TestUtils.reverse(inputs[i]));

            testCases[i] = testCase;
        }

        return testCases;
    }

    private static Object[][] getInputs() {
        return new Object[][] {
                new Object[] { 1 },
                new Object[] { 1.1, 2.2 },
                new Object[] { 0, "", true, new Double[10], null },
        };
    }
}
