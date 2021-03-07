package edu.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import edu.StaticArrayList;
import edu.StaticArrayListException;

public class StaticArrayListTest {

	/*
	 	Tarefa
	 	
	 		Implementar, usando internamente um array, uma lista genérica de 
	 		tamanho máximo pré-definido e que possua as seguintes operações:
	 		
	  		StaticArrayList(int capacity)
	  			Cria uma lista genérica com a capacidade dada caso esta seja 
	  			maior ou igual a zero.
	  			
	  			Lança uma StaticArrayListException caso contrário.

	  		int size() 
	  			Informa o tamanho atual (<= capacidade) da lista.
	  			
	  			
	  		T get(int index)
	  			Retorna o item de índice dado se este for maior ou igual a zero
	  			e se for menor do que o tamanho atual da lista.
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			
	  			
			void prepend(T item)
	  			Insere o item dado no início da lista caso a mesma não tenha
	  			atingido sua capacidade.
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			
	  			
	  		void append(T item)
	  			Insere o item dado no final da lista caso a mesma não tenha
	  			atingido sua capacidade. 
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			

	  		void insert(int index, T item)
	  			Insere o item dado desde que:
	  				
	  				1) o índice dado for maior ou igual a zero;
	  				2) o índice dado for menor ou igual ao tamanho atual da lista;
	  				
	  			Lança uma StaticArrayListException caso contrário.
	  			
	  			
	  		void unprepend()
	  			Remove o primeiro item da lista caso a mesma não esteja vazia.
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			
	 */
	
	@Test(expected = StaticArrayListException.class)
	public void cannotCreateListWithNegativeCapacity() {
		new StaticArrayList<Object>(-1);
	}

	@Test(expected = StaticArrayListException.class)
	public void cannotPrependToNilList() {
		StaticArrayList<Object> nilList = new StaticArrayList<Object>(0);
		nilList.prepend("");
	}
	
	@Test(expected = StaticArrayListException.class)
	public void cannotAppendToNilList() {
		StaticArrayList<Object> nilList = new StaticArrayList<Object>(0);
		nilList.append("");
	}
	
	@Test(expected = StaticArrayListException.class)
	public void cannotInsertInNilList() {
		StaticArrayList<Object> nilList = new StaticArrayList<Object>(0);
		nilList.insert(3, "");
	}
	
	@Test(expected = StaticArrayListException.class)
	public void cannotUnprependToNilList() {
		StaticArrayList<Object> nilList = new StaticArrayList<Object>(0);
		nilList.unprepend();
	}
	
//	@Test(expected = StaticArrayListException.class)
//	public void cannotUnprependEmptyList() {
//		StaticArrayList<Object> emptyList = new StaticArrayList<Object>(10);
//		emptyList.unprepend();
//	}
//	
	@Test
	public void canCreateNilList() {
		StaticArrayList<Object> nilList = new StaticArrayList<Object>(0);
		assertTrue(nilList.size() == 0);
	}
	
	@Test
	public void canPrependToList() {
		InsertionSample[] prependingSamples = getPrependingSamples();
		
		for (InsertionSample prependSample : prependingSamples) {
			
			StaticArrayList<Object> list = new StaticArrayList<Object>(prependSample.getSampleCapacity());
			Object[] sample = prependSample.getInitialSample();
			
			for (Object item : sample) {
				list.prepend(item);
			}
			
			assertListsAreEqual(prependSample.getFinalSample(), list);
		}
	}
	
	@Test
	public void canAppendToList() {
		InsertionSample[] appendingSamples = getAppendingSamples();
		
		for (InsertionSample appendSample : appendingSamples) {
			
			StaticArrayList<Object> list = new StaticArrayList<Object>(appendSample.getSampleCapacity());
			Object[] sample = appendSample.getInitialSample();
			
			for (Object item : sample) {
				list.append(item);
			}
			
			assertListsAreEqual(sample, list);
		}
	}
	
	@Test
	public void canInsertToList() {
		InsertionSample[] insertionSamples = getInsertionSamples();
		
		for (InsertionSample insertionSample : insertionSamples) {
			StaticArrayList<Object> list = new StaticArrayList<Object>(insertionSample.getSampleCapacity());
			Object[] initialSample = insertionSample.getInitialSample();

			for (int i = 0; i < initialSample.length; i++) {
				list.insert(i, initialSample[i]);
			}
			list.insert(insertionSample.getInsertionIndex(), insertionSample.getObjectToBeInserted());
			
			assertListsAreEqual(insertionSample.getFinalSample(), list);
		}
	}
	
	
	
	
	private InsertionSample[] getPrependingSamples() {
		Object[][] initialSamples = getPrependingInitialSamples();
		InsertionSample[] prependingSamples = new InsertionSample[initialSamples.length];
		
		for (int i = 0; i < initialSamples.length; i++) {
			prependingSamples[i] = new InsertionSample()
										.sampleCapacity(initialSamples[i].length)
										.initialSample(initialSamples[i])
										.finalSample(reverse(initialSamples[i]));
		}
		
		return prependingSamples;
	}
	
	private InsertionSample[] getAppendingSamples() {
		Object[][] initialSamples = getAppendingInitialSamples();
		InsertionSample[] appendingSamples = new InsertionSample[initialSamples.length];
		
		for (int i = 0; i < initialSamples.length; i++) {
			appendingSamples[i] = new InsertionSample()
										.sampleCapacity(initialSamples[i].length)
										.initialSample(initialSamples[i])
										.finalSample(initialSamples[i]);
		}
		
		return appendingSamples;
	}
	
	private InsertionSample[] getInsertionSamples() {
		int sampleCapacity = 10;
		Object objectToBeInserted = "X";
		Object[] initialSample = new Object[] { "A", "B", "C", "D", "E" };
		
		InsertionSample[] insertionSamples = new InsertionSample[1 + initialSample.length];
		
		for (int i = 0; i < insertionSamples.length; i++) {
			insertionSamples[i] = new InsertionSample()
									.insertionIndex(i)
									.objectToBeInserted(objectToBeInserted)
									.sampleCapacity(sampleCapacity)
									.initialSample(initialSample)
									.finalSample(getInsertionFinalSample(i, objectToBeInserted, initialSample));
		}
		
		return insertionSamples;
	}
	
	private Object[][] getPrependingInitialSamples() {
		return new Object[][] {
			new Object[] { 1 },
			new Object[] { 1.1, 2.2 },
			new Object[] { 0, "", true, new Double[10], null },
		};
	}
	
	private Object[][] getAppendingInitialSamples() {
		return new Object[][] {
			new Object[] { true },
			new Object[] { "1.99f", "-3.14159f" },
			new Object[] { null, new Object(), new Object(), new Object[100], null }
		};
	}
	
	private Object[] getInsertionFinalSample(int insertionIndex, Object objectToBeInserted, Object[] initialSample) {
		Object[] finalSample = new Object[1 + initialSample.length];

		for (int i = 0, j = 0; i < initialSample.length; i++, j++) {
			if (i == insertionIndex) {
				finalSample[j] = objectToBeInserted;
				finalSample[j + 1] = initialSample[i];
				j++;
			}
			else {
				finalSample[j] = initialSample[i];
			}
		}
		
		if (insertionIndex > initialSample.length - 1) {
			finalSample[insertionIndex] = objectToBeInserted;
		}
		
		return finalSample;
	}

	private Object[] reverse(Object[] items) {
		Object[] reversed = new Object[items.length];
		
		for (int i = 0; i < items.length; i++) {
			reversed[items.length - 1 - i] = items[i];
		}
		
		return reversed;
	}
	
	private void assertListsAreEqual(Object[] sample, StaticArrayList<Object> list) {
		assertTrue(sample.length == list.size());
		
		for (int i = 0; i < sample.length; i++) {
			assertEquals(sample[i], list.get(i));
		}
	}
}
