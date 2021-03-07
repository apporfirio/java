package edu;

import java.util.Arrays;

public class StaticArrayList<T> {

	private int size;
	private Object[] container;
	
	public StaticArrayList(int capacity) {
		assertNonNegative("capacity", capacity);
		this.size = 0;
		this.container = new Object[capacity];
	}
	
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked") 
	public T get(int index) {
		asserLookUpIndex(index);
		return (T) container[index];
	}
	
	public void prepend(T item) {
		insert(0, item);
	}
	
	public void append(T item) {
		insert(size, item);
	}
	
	public void insert(int index, T item) {
		assertNotFull();
		assertInsertionIndex(index);
		
		if (index < size) shiftRight(index);
		
		container[index] = item;
		size++;
	}
	
	public void unprepend() {
	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(container);
	}

	
	
	private void shiftRight(int startIndex) {
		for (int i = size - 1; i >= startIndex; i--) {
			container[i + 1] = container[i];
		}
	}
	
	private void assertNonNegative(String valueName, int value) {
		if (value < 0) {
			throw new StaticArrayListException(valueName + " cannot be negative");
		}
	}
	
	private void assertNotFull() {
		if (size == container.length) {
			throw new StaticArrayListException("instance has reached its capacity");
		}
	}
	
	private void assertInsertionIndex(int index) {
		if (index < 0 || index > size) {
			throw new StaticArrayListException("invalid insertion index (" + index + ")" + " for current size (" + size + ")");
		}
	}
	
	private void asserLookUpIndex(int index) {
		if (index < 0 || index > size - 1) {
			throw new StaticArrayListException("invalid index (" + index + ") to look up " + " for current size (" + size + ")");
		}
	}
}
