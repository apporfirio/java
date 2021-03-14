package edu.test.models;

import edu.StaticArrayList;

public class InsertTestCase {

    private int givenIndex;
    private Object givenItem;
    private StaticArrayList<Object> givenList;
    private StaticArrayList<Object> expectedList;

    public int getGivenIndex() {
        return givenIndex;
    }
    public void setGivenIndex(int givenIndex) {
        this.givenIndex = givenIndex;
    }

    public Object getGivenItem() {
        return givenItem;
    }
    public void setGivenItem(Object givenItem) {
        this.givenItem = givenItem;
    }

    public StaticArrayList<Object> getGivenList() {
        return givenList;
    }
    public void setGivenList(StaticArrayList<Object> givenList) {
        this.givenList = givenList;
    }

    public StaticArrayList<Object> getExpectedList() {
        return expectedList;
    }
    public void setExpectedList(StaticArrayList<Object> expectedList) {
        this.expectedList = expectedList;
    }
}
