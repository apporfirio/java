package edu.test.helpers;

import edu.StaticArrayList;
import edu.test.models.InsertTestCase;

public class InsertTestHelper {

    private static Object[] initialItems = new Object[] { "A", "B", "C", "D", "E" };
    private static Object givenItem = "X";

    public static InsertTestCase[] getTestCases() {
        InsertTestCase[] testCases = new InsertTestCase[1 + initialItems.length];

        for (int i = 0; i < testCases.length; i++) {
            InsertTestCase testCase = new InsertTestCase();

            testCase.setGivenIndex(i);
            testCase.setGivenItem(givenItem);
            testCase.setGivenList(new StaticArrayList<>(1 + initialItems.length, initialItems));
            testCase.setExpectedList(new StaticArrayList<>(1 + initialItems.length, createExpectedItems(i)));

            testCases[i] = testCase;
        }

        return testCases;
    }

    private static Object[] createExpectedItems(int insertionIndex) {
        Object[] expectedItems = new Object[1 + initialItems.length];

        for (int i = 0, j = 0; i < initialItems.length; i++, j++) {
            if (i == insertionIndex) {
                expectedItems[j] = givenItem;
                expectedItems[j + 1] = initialItems[i];
                j++;
            }
            else {
                expectedItems[j] = initialItems[i];
            }
        }

        if (insertionIndex == initialItems.length) {
            expectedItems[insertionIndex] = givenItem;
        }

        return expectedItems;
    }
}
