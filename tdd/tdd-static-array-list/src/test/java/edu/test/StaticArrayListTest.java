package edu.test;

import static org.junit.Assert.assertEquals;

import edu.test.helpers.InsertTestHelper;
import edu.test.helpers.RemoveFirstTestHelper;
import edu.test.models.InsertFirstTestCase;
import edu.test.models.InsertLastTestCase;
import edu.test.helpers.InsertFirstTestHelper;
import edu.test.helpers.InsertLastTestHelper;
import edu.test.models.InsertTestCase;
import edu.test.models.RemoveFirstTestCase;
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

	  		StaticArrayList(int capacity, Object[] initialItems)
	  			Cria uma lista genérica com a capacidade dada caso esta seja
	  			maior ou igual a zero. Lança uma StaticArrayListException caso contrário.

	  			Copia os itens informados para a lista.

	  		int size() 
	  			Informa o tamanho atual (<= capacidade) da lista.
	  			
	  			
	  		T get(int index)
	  			Retorna o item de índice dado se este for maior ou igual a zero
	  			e se for menor do que o tamanho atual da lista.
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			
	  			
			void insertFirst(T item)
	  			Insere o item dado no início da lista caso a mesma não tenha
	  			atingido sua capacidade.
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			
	  			
	  		void insertLast(T item)
	  			Insere o item dado no final da lista caso a mesma não tenha
	  			atingido sua capacidade. 
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			

	  		void insert(int index, T item)
	  			Insere o item dado desde que:
	  				
	  				1) o índice dado for maior ou igual a zero;
	  				2) o índice dado for menor ou igual ao tamanho atual da lista;
	  				
	  			Lança uma StaticArrayListException caso contrário.
	  			
	  			
	  		void removeFirst()
	  			Remove o primeiro item da lista caso a mesma não esteja vazia.
	  			
	  			Lança uma StaticArrayListException caso contrário.
	  			
	 */
	
	@Test(expected = StaticArrayListException.class)
	public void cannotCreateListWithNegativeCapacityWithFirstConstructor() {
		new StaticArrayList<>(-1);
	}

	@Test(expected = StaticArrayListException.class)
	public void cannotCreateListWithNegativeCapacityWithSecondConstructor() {
		new StaticArrayList<>(-1, new Object[10]);
	}

	@Test(expected = StaticArrayListException.class)
	public void cannotInsertFirstInZeroSizedList() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(0);
		zeroSizedList.insertFirst("");
	}
	
	@Test(expected = StaticArrayListException.class)
	public void cannotInsertLastInZeroSizedList() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(0);
		zeroSizedList.insertLast("");
	}
	
	@Test(expected = StaticArrayListException.class)
	public void cannotInsertInZeroSizedList() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(0);
		zeroSizedList.insert(3, "");
	}
	
	@Test(expected = StaticArrayListException.class)
	public void cannotRemoveFirstFromZeroSizedList() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(0);
		zeroSizedList.removeFirst();
	}

	@Test(expected = StaticArrayListException.class)
	public void cannotRemoveFirstFromEmptyList() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(10);
		zeroSizedList.removeFirst();
	}

	@Test
	public void canCreateZeroSizedListWithFirstConstructor() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(0);
		assertEquals(0, zeroSizedList.size());
	}

	@Test
	public void canCreateZeroSizedListWithSecondConstructor() {
		StaticArrayList<Object> zeroSizedList = new StaticArrayList<>(0, new Object[10]);
		assertEquals(0, zeroSizedList.size());
	}

	@Test
	public void canInitializeListWithAllInitialItems() {
		Object[] initialItems = new Object[]{ "A", "B", "C"};
		StaticArrayList<Object> list = new StaticArrayList<>(5, initialItems);

		assertEquals(3, list.size());

		for (int i = 0; i < initialItems.length; i++) {
			assertEquals(initialItems[i], list.get(i));
		}
	}

	@Test
	public void canInitializeListWithSomeInitialItems() {
		Object[] initialItems = new Object[]{ "A", "B", "C"};
		StaticArrayList<Object> list = new StaticArrayList<>(1, initialItems);

		assertEquals(1, list.size());

		for (int i = 0; i < list.size(); i++) {
			assertEquals(initialItems[i], list.get(i));
		}
	}
	
	@Test
	public void canInsertFirstInList() {
		InsertFirstTestCase[] testCases = InsertFirstTestHelper.getTestCases();
		
		for (InsertFirstTestCase testCase : testCases) {
			StaticArrayList<Object> list = new StaticArrayList<>(testCase.getGivenCapacity());

			Object[] givenItems = testCase.getGivenItems();
			for (Object item : givenItems) {
				list.insertFirst(item);
			}
			
			assertResult(testCase.getExpectedItems(), list);
		}
	}
	
	@Test
	public void canInsertLastInList() {
		InsertLastTestCase[] testCases = InsertLastTestHelper.getTestCases();
		
		for (InsertLastTestCase testCase : testCases) {
			StaticArrayList<Object> list = new StaticArrayList<>(testCase.getGivenCapacity());

			Object[] givenItems = testCase.getGivenItems();
			for (Object item : givenItems) {
				list.insertLast(item);
			}
			
			assertResult(testCase.getExpectedItems(), list);
		}
	}
	
	@Test
	public void canInsertInList() {
		InsertTestCase[] testCases = InsertTestHelper.getTestCases();
		
		for (InsertTestCase testCase : testCases) {
			StaticArrayList<Object> list = testCase.getGivenList();

			list.insert(testCase.getGivenIndex(), testCase.getGivenItem());

			assertResult(testCase.getExpectedList(), list);
		}
	}

	@Test
	public void canRemoveFirstFromList() {
		RemoveFirstTestCase[] testCases = RemoveFirstTestHelper.getTestCases();

		for (RemoveFirstTestCase testCase : testCases) {
			StaticArrayList<Object> list = testCase.getGivenList();

			list.removeFirst();

			assertResult(testCase.getExpectedList(), list);
		}
	}

	@Test
	public void canRemoveLastFromList() {
		// TODO
	}

	private void assertResult(Object[] expected, StaticArrayList<Object> given) {
		assertEquals(expected.length, given.size());

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], given.get(i));
		}
	}

	private void assertResult(StaticArrayList<Object> expected, StaticArrayList<Object> given) {
		assertEquals(expected.size(), given.size());

		for (int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i), given.get(i));
		}
	}
}
