package edu.test.helpers;

import edu.test.utils.TestUtils;
import edu.test.models.InsertFirstTestCase;

public class InsertFirstTestHelper {

    private static Object[][] givenInputs;

    public static InsertFirstTestCase[] getTestCases() {
        Object[][] inputs = getGivenInputs();
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

    private static Object[][] getGivenInputs() {
        if (givenInputs == null) {
            givenInputs = new Object[][] {
                    new Object[] { 1 },
                    new Object[] { 1.1, 2.2 },
                    new Object[] { new String[3][4], new Object(), new Integer[1], new Double[10], null },
            };
        }

        return givenInputs;
    }
}
