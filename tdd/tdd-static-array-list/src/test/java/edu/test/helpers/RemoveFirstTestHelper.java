package edu.test.helpers;

import edu.StaticArrayList;
import edu.test.models.RemoveFirstTestCase;

public class RemoveFirstTestHelper {

    private static Object[][] givenInputs;

    public static RemoveFirstTestCase[] getTestCases() {
        Object[][] inputs = getGivenInputs();
        Object[][] outputs = getExpectedOutputs();
        RemoveFirstTestCase[] testCases = new RemoveFirstTestCase[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            RemoveFirstTestCase testCase = new RemoveFirstTestCase();
            Object[] input = inputs[i];
            Object[] output = outputs[i];

            testCase.setGivenList(new StaticArrayList<>(input.length, input));
            testCase.setExpectedList(new StaticArrayList<>(output.length, output));

            testCases[i] = testCase;
        }

        return testCases;
    }

    private static Object[][] getExpectedOutputs() {
        Object[][] inputs = getGivenInputs();
        Object[][] outputs = new Object[inputs.length][];

        for (int i = 0; i < inputs.length; i++) {
            Object[] input = inputs[i];
            Object[] output = new Object[input.length - 1];

            for (int j = 1; j < input.length; j++) {
                output[j - 1] = input[j];
            }

            outputs[i] = output;
        }

        return outputs;
    }

    private static Object[][] getGivenInputs() {
        if (givenInputs == null) {
            givenInputs = new Object[][] {
                    new Object[] { true },
                    new Object[] { 3, -3.14f },
                    new Object[] { 'a', 'b',  "AB" },
                    new Object[] { null, new Integer[3], new Object(), new Object[10][10], new Integer[5] }
            };
        }

        return givenInputs;
    }
}
