package edu.test;

public class InsertionSample {

	private int insertionIndex;
	private int sampleCapacity;
	private Object objectToBeInserted;
	private Object[] initialSample;
	private Object[] finalSample;

	public int getSampleCapacity() {
		return sampleCapacity;
	}
	public InsertionSample sampleCapacity(int sampleCapacity) {
		this.sampleCapacity = sampleCapacity;
		return this;
	}
	
	public int getInsertionIndex() {
		return insertionIndex;
	}
	public InsertionSample insertionIndex(int insertionIndex) {
		this.insertionIndex = insertionIndex;
		return this;
	}

	public Object getObjectToBeInserted() {
		return objectToBeInserted;
	}
	public InsertionSample objectToBeInserted(Object objectToBeInserted) {
		this.objectToBeInserted = objectToBeInserted;
		return this;
	}

	public Object[] getInitialSample() {
		return initialSample;
	}
	public InsertionSample initialSample(Object[] initialSample) {
		this.initialSample = initialSample;
		return this;
	}

	public Object[] getFinalSample() {
		return finalSample;
	}
	public InsertionSample finalSample(Object[] finalSample) {
		this.finalSample = finalSample;
		return this;
	}
	
}
