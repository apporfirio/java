package edu.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import edu.DMYDate;
import edu.InvalidDMYDate;

public class DMYDateTest {

	/*
	 	Tarefa
	 	
	 		Implementar um tipo de dado chamado DMYDate para representar uma data no formato dd/mm/yyyy (dia, mês, ano)
	 		e que ofereça as seguintes operações:

			DMYDate(int day, int month, int year)
				Cria uma data caso a mesma seja válida. Do contrário, 
				retorna uma exceção do tipo DMYDate.Invalid;

			DMYDate(String date)
				Cria uma data à partir de uma string no formato dd/mm/yyyy caso a data informada seja válida. Do contrário,
				retorna uma exceção DMYDate.Invalid;

			String toLiteralString()
				Retorna uma string com a data escrita por extenso.
				
				Exemplo: 01/05/2005 fica 1 May 2005
					
			DMYDate plusDays(int days)
				Cria uma data à partir da soma do número de dias informado com a data em questão;

			DMYDate minusDays(int days)
				Cria uma data à partir da subtração do número de dias informado com a data em questão;


		Critérios de validação
	  			
	  		Considerar datas à partir de 01 de Janeiro de 1583 (por simplicidade, já que no ano anterior, 
	  		que marca o início da vigência do calendário atual, isto é, o calendário Gregoriano, pularam-se
	  		10 dias, saltando-se de 04 de Outubro para 15 de Outubro);
	  			
	  		Considerar anos bissextos;
	  		
	  		Meses com 30 dias: 04, 06, 09, 11;
	  		
	  		Meses com 31 dias: 01, 03, 05, 07, 08, 10, 12;
	 */
	
	@Test
	public void cannotCreateInvalidDatesAsIntegers() {
		List<List<Integer>> samples = getInvalidDatesAsIntegers();
		int exceptionCounter = 0;
		
		for (List<Integer> sample : samples) {
			try {
				new DMYDate(sample.get(0), sample.get(1), sample.get(2));
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(samples.size(), exceptionCounter);
	}
	
	@Test
	public void cannotCreateInvalidDatesAsStrings() {
		List<String> samples = getInvalidDatesAsStrings();
		int exceptionCounter = 0;
		
		for (String sample : samples) {
			try {
				new DMYDate(sample);
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(samples.size(), exceptionCounter);
	}

	@Test
	public void canCreateValidDatesAsIntegers() {
		Set<List<Integer>> samples = getValidDatesAsIntegers().keySet();
		int exceptionCounter = 0;
		
		for (List<Integer> sample : samples) {
			try {
				new DMYDate(sample.get(0), sample.get(1), sample.get(2));
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(0, exceptionCounter);
	}

	@Test
	public void canCreateValidDatesAsStrings() {
		Set<String> samples = getValidDatesAsStrings().keySet();
		int exceptionCounter = 0;
		
		for (String sample : samples) {
			try {
				new DMYDate(sample);
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(0, exceptionCounter);
	}

	@Test
	public void canRepresentAsStringValidDatesAsIntegers() {
		int exceptionCounter = 0;

		for (Map.Entry<List<Integer>, String> sample : getValidDatesAsIntegers().entrySet()) {
			try {
				List<Integer> date = sample.getKey();
				String dateAsString = sample.getValue();
				
				assertEquals(dateAsString, new DMYDate(date.get(0), date.get(1), date.get(2)).toString());
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(0, exceptionCounter);
	}

	@Test
	public void canRepresentAsStringValidDatesAsStrings() {
		int exceptionCounter = 0;

		for (Map.Entry<String, String> sample : getValidDatesAsStrings().entrySet()) {
			try {
				String date = sample.getKey();
				String dateAsString = sample.getValue();
				
				assertEquals(dateAsString, new DMYDate(date).toString());
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(0, exceptionCounter);
	}

	
	@Test
	public void canAddDaysToValidDatesAsIntegers() {
		int exceptionCounter = 0;

		for (List<Integer> sample : getDaysToAddToValidDatesAsIntegersSamples()) {
			try {
				DMYDate start = new DMYDate(sample.get(0), sample.get(1), sample.get(2));
				DMYDate result = start.plusDays(sample.get(3));
				DMYDate target = new DMYDate(sample.get(4), sample.get(5), sample.get(6));
				
				assertTrue(target.equals(result));
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(0, exceptionCounter);
	}
	
	@Test
	public void cannotSubtractInvalidDaysFromValidDatesAsStrings() {
		int exceptionCounter = 0;
		List<List<String>> samples = getInvalidDaysToSubtracFromValidDatesAsStrings();
		
		for (List<String> sample : samples) {
			try {
				DMYDate start = new DMYDate(sample.get(0));
				start.minusDays(Integer.valueOf(sample.get(1)));
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(samples.size(), exceptionCounter);
	}
	
	@Test
	public void canSubtractValidDaysFromValidDatesAsStrings() {
		int exceptionCounter = 0;

		for (List<String> sample : getValidDaysToSubtractFromValidDatesAsStrings()) {
			try {
				DMYDate start = new DMYDate(sample.get(0));
				DMYDate result = start.minusDays(Integer.valueOf(sample.get(1)));
				DMYDate target = new DMYDate(sample.get(2));
				
				assertTrue(target.equals(result));
			}
			catch (InvalidDMYDate e) {
				exceptionCounter++;
			}
		}
		
		assertEquals(0, exceptionCounter);
	}
	
	
	private List<List<Integer>> getInvalidDatesAsIntegers() {
		List<List<Integer>> invalidDates = new ArrayList<List<Integer>>();
	
		invalidDates.add(Arrays.asList(1, 3, -1700));
		invalidDates.add(Arrays.asList(1, 2, 0));
		invalidDates.add(Arrays.asList(1, 1, 1500));
		invalidDates.add(Arrays.asList(30, 5, 1582));
		invalidDates.add(Arrays.asList(12, 10, 1582));
		
		invalidDates.add(Arrays.asList(30, -1, 2000));
		invalidDates.add(Arrays.asList(01, 0, 2000));
		invalidDates.add(Arrays.asList(23, 13, 2000));
		
		invalidDates.add(Arrays.asList(-3, 1, 2000));
		invalidDates.add(Arrays.asList(0, 1, 2000));
		invalidDates.add(Arrays.asList(32, 1, 2000));
		invalidDates.add(Arrays.asList(40, 6, 2000));
		invalidDates.add(Arrays.asList(31, 4, 2000));
		invalidDates.add(Arrays.asList(29, 2, 1999));

		return invalidDates;
	}
	
	private List<String> getInvalidDatesAsStrings() {
		List<String> invalidDates = new ArrayList<String>();
		
		// format error
		invalidDates.add("-03/01/2000");
		invalidDates.add("30/-01/2000");
		invalidDates.add("01/03/-2000");
		invalidDates.add("01/02/0");
		
		// value error
		invalidDates.add("12/12/1000");
		invalidDates.add("06/06/1582");
		invalidDates.add("14/10/1582");
		invalidDates.add("0/1/2000");
		invalidDates.add("1/0/2000");
		invalidDates.add("01/01/1500");
		invalidDates.add("23/13/2000");
		invalidDates.add("32/1/2000");
		invalidDates.add("40/6/2000");
		invalidDates.add("31/4/2000");
		invalidDates.add("29/2/1999");
		
		return invalidDates;
	}
	
	private Map<List<Integer>, String> getValidDatesAsIntegers() {
		Map<List<Integer>, String> validDates = new HashMap<List<Integer>, String>();
		
		validDates.put(Arrays.asList(1, 1, 1583), "1 January 1583");
		validDates.put(Arrays.asList(1, 1, 1601), "1 January 1601");
		validDates.put(Arrays.asList(29, 2, 2004), "29 February 2004");
		
		validDates.put(Arrays.asList(29, 4, 2004), "29 April 2004");
		validDates.put(Arrays.asList(30, 4, 3500), "30 April 3500");

		validDates.put(Arrays.asList(29, 5, 2011), "29 May 2011");
		validDates.put(Arrays.asList(30, 5, 2011), "30 May 2011");
		validDates.put(Arrays.asList(31, 5, 2011), "31 May 2011");
		
		return validDates;
	}
	
	private List<List<Integer>> getDaysToAddToValidDatesAsIntegersSamples() {
		List<List<Integer>> validDates = new ArrayList<List<Integer>>();

		// Soma 0 para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList(1,  2, 1800, 0, 1,  2, 1800));
		validDates.add(Arrays.asList(10, 2, 1800, 0, 10, 2, 1800));
		validDates.add(Arrays.asList(28, 2, 1800, 0, 28, 2, 1800));
		
		validDates.add(Arrays.asList(1,  2, 1584, 0, 1,  2, 1584));
		validDates.add(Arrays.asList(10, 2, 1584, 0, 10, 2, 1584));
		validDates.add(Arrays.asList(29, 2, 1584, 0, 29, 2, 1584));
		
		validDates.add(Arrays.asList(1,  4, 2001, 0, 1,  4, 2001));
		validDates.add(Arrays.asList(10, 4, 2001, 0, 10, 4, 2001));
		validDates.add(Arrays.asList(30, 4, 2001, 0, 30, 4, 2001));
		
		validDates.add(Arrays.asList(1,  1, 1583, 0, 1,  1, 1583));
		validDates.add(Arrays.asList(10, 1, 1583, 0, 10, 1, 1583));
		validDates.add(Arrays.asList(31, 1, 1583, 0, 31, 1, 1583));

		// Soma tamanho do mês - 1 para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList(2,  2, 1709, 27, 1,  3, 1709));
		validDates.add(Arrays.asList(11, 2, 1709, 27, 10, 3, 1709));
		validDates.add(Arrays.asList(28, 2, 1709, 27, 27, 3, 1709));
		
		validDates.add(Arrays.asList(2,  2, 1624, 28, 1,  3, 1624));
		validDates.add(Arrays.asList(11, 2, 1624, 28, 10,  3, 1624));
		validDates.add(Arrays.asList(29, 2, 1624, 28, 28, 3, 1624));

		validDates.add(Arrays.asList(2,  6, 4111, 29, 1,  7, 4111));
		validDates.add(Arrays.asList(11, 6, 4111, 29, 10,  7, 4111));
		validDates.add(Arrays.asList(30, 6, 4111, 29, 29, 7, 4111));

		validDates.add(Arrays.asList(2,  3, 1999, 30, 1,  4, 1999));
		validDates.add(Arrays.asList(11, 3, 1999, 30, 10, 4, 1999));
		validDates.add(Arrays.asList(31, 3, 1999, 30, 30, 4, 1999));
		
		// Soma tamanho do mês para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList(3,  2, 1666, 28, 3,  3, 1666));
		validDates.add(Arrays.asList(12, 2, 1666, 28, 12, 3, 1666));
		validDates.add(Arrays.asList(28, 2, 1666, 28, 28, 3, 1666));

		validDates.add(Arrays.asList(3,  2, 1736, 29, 3,  3, 1736));
		validDates.add(Arrays.asList(12, 2, 1736, 29, 12, 3, 1736));
		validDates.add(Arrays.asList(29, 2, 1736, 29, 29, 3, 1736));
		
		validDates.add(Arrays.asList(3,  9, 2033, 30, 3,  10, 2033));
		validDates.add(Arrays.asList(12, 9, 2033, 30, 12, 10, 2033));
		validDates.add(Arrays.asList(30, 9, 2033, 30, 30, 10, 2033));

		validDates.add(Arrays.asList(3,  5, 1999, 31, 3,  6, 1999));
		validDates.add(Arrays.asList(12, 5, 1999, 31, 12, 6, 1999));
		validDates.add(Arrays.asList(31, 5, 1999, 31, 1, 7, 1999));
		
		// Soma tamanho do mês + 1 para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList(4,  2, 1599, 29, 5,  3, 1599));
		validDates.add(Arrays.asList(13, 2, 1599, 29, 14, 3, 1599));
		validDates.add(Arrays.asList(28, 2, 1599, 29, 29, 3, 1599));

		validDates.add(Arrays.asList(4,  2, 1808, 30, 5,  3, 1808));
		validDates.add(Arrays.asList(13, 2, 1808, 30, 14, 3, 1808));
		validDates.add(Arrays.asList(29, 2, 1808, 30, 30, 3, 1808));

		validDates.add(Arrays.asList(4,  11, 2000, 31, 5,  12, 2000));
		validDates.add(Arrays.asList(13, 11, 2000, 31, 14, 12, 2000));
		validDates.add(Arrays.asList(30, 11, 2000, 31, 31, 12, 2000));

		validDates.add(Arrays.asList(4,  7, 1903, 32, 5,  8, 1903));
		validDates.add(Arrays.asList(13, 7, 1903, 32, 14, 8, 1903));
		validDates.add(Arrays.asList(31, 7, 1903, 32, 1,  9, 1903));
		
		// Soma de meio ano para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList(5,  2, 1737, 182, 6,  8, 1737));
		validDates.add(Arrays.asList(14, 2, 1737, 182, 15, 8, 1737));
		validDates.add(Arrays.asList(28, 2, 1737, 182, 29, 8, 1737));

		validDates.add(Arrays.asList(5,  2, 1888, 183, 6,  8, 1888));
		validDates.add(Arrays.asList(14, 2, 1888, 183, 15, 8, 1888));
		validDates.add(Arrays.asList(29, 2, 1888, 183, 30, 8, 1888));

		validDates.add(Arrays.asList(5,  4, 2204, 183, 5,  10, 2204));
		validDates.add(Arrays.asList(14, 4, 2204, 183, 14, 10, 2204));
		validDates.add(Arrays.asList(30, 4, 2204, 183, 30, 10, 2204));

		validDates.add(Arrays.asList(5,  8, 1959, 182, 3,  2, 1960));
		validDates.add(Arrays.asList(14, 8, 1959, 182, 12, 2, 1960));
		validDates.add(Arrays.asList(31, 8, 1959, 182, 29, 2, 1960));
		
		// Soma tamanho do ano para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList(6,  2, 1597, 365, 6,  2, 1598));
		validDates.add(Arrays.asList(15, 2, 1597, 365, 15, 2, 1598));
		validDates.add(Arrays.asList(28, 2, 1597, 365, 28, 2, 1598));

		validDates.add(Arrays.asList(6,  2, 1644, 366, 6,  2, 1645));
		validDates.add(Arrays.asList(15, 2, 1644, 366, 15, 2, 1645));
		validDates.add(Arrays.asList(29, 2, 1644, 366, 1,  3, 1645));

		validDates.add(Arrays.asList(6,  9, 8000, 366, 7,  9, 8001));
		validDates.add(Arrays.asList(15, 9, 8000, 366, 16, 9, 8001));
		validDates.add(Arrays.asList(30, 9, 8000, 366, 1, 10, 8001));

		validDates.add(Arrays.asList(6,  10, 1715, 365, 5,  10, 1716));
		validDates.add(Arrays.asList(15, 10, 1715, 365, 14, 10, 1716));
		validDates.add(Arrays.asList(31, 10, 1715, 365, 30, 10, 1716));
		
		return validDates;
	}
	
	private List<List<String>> getInvalidDaysToSubtracFromValidDatesAsStrings() {
		List<List<String>> invalidDates = new ArrayList<List<String>>();
		
		invalidDates.add(Arrays.asList("1/1/1583", "1"));
		invalidDates.add(Arrays.asList("28/2/1583", "60"));
		invalidDates.add(Arrays.asList("15/9/8000", "3000000"));

		return invalidDates;
	}
	
	private List<List<String>> getValidDaysToSubtractFromValidDatesAsStrings() {
		List<List<String>> validDates = new ArrayList<List<String>>();
		
		// Subtrai 0 para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList("1/2/1800",  "0", "1/2/1800"));
		validDates.add(Arrays.asList("10/2/1800", "0", "10/2/1800"));
		validDates.add(Arrays.asList("28/2/1800", "0", "28/2/1800"));
		
		validDates.add(Arrays.asList("1/2/1584",  "0", "1/2/1584"));
		validDates.add(Arrays.asList("10/2/1584", "0", "10/2/1584"));
		validDates.add(Arrays.asList("29/2/1584", "0", "29/2/1584"));
		
		validDates.add(Arrays.asList("1/4/2001",  "0", "1/4/2001"));
		validDates.add(Arrays.asList("10/4/2001", "0", "10/4/2001"));
		validDates.add(Arrays.asList("30/4/2001", "0", "30/4/2001"));
		
		validDates.add(Arrays.asList("1/1/1583",  "0", "1/1/1583"));
		validDates.add(Arrays.asList("10/1/1583", "0", "10/1/1583"));
		validDates.add(Arrays.asList("31/1/1583", "0", "31/1/1583"));
		
		// Subtrai tamanho do mês - 1 para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList("2/2/1709",  "27", "6/1/1709"));
		validDates.add(Arrays.asList("11/2/1709", "27", "15/1/1709"));
		validDates.add(Arrays.asList("28/2/1709", "27", "1/2/1709"));
		
		validDates.add(Arrays.asList("2/2/1624",  "28", "5/1/1624"));
		validDates.add(Arrays.asList("11/2/1624", "28", "14/1/1624"));
		validDates.add(Arrays.asList("29/2/1624", "28", "1/2/1624"));

		validDates.add(Arrays.asList("2/6/4111",  "29", "4/5/4111"));
		validDates.add(Arrays.asList("11/6/4111", "29", "13/5/4111"));
		validDates.add(Arrays.asList("30/6/4111", "29", "1/6/4111"));

		validDates.add(Arrays.asList("2/3/1999",  "30", "31/1/1999"));
		validDates.add(Arrays.asList("11/3/1999", "30", "9/2/1999"));
		validDates.add(Arrays.asList("31/3/1999", "30", "1/3/1999"));
		
		// Subtrai tamanho do mês para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList("3/2/1666",  "28", "6/1/1666"));
		validDates.add(Arrays.asList("12/2/1666", "28", "15/1/1666"));
		validDates.add(Arrays.asList("28/2/1666", "28", "31/1/1666"));

		validDates.add(Arrays.asList("3/2/1736",  "29", "5/1/1736"));
		validDates.add(Arrays.asList("12/2/1736", "29", "14/1/1736"));
		validDates.add(Arrays.asList("29/2/1736", "29", "31/1/1736"));

		validDates.add(Arrays.asList("3/9/2033",  "30", "4/8/2033"));
		validDates.add(Arrays.asList("12/9/2033", "30", "13/8/2033"));
		validDates.add(Arrays.asList("30/9/2033", "30", "31/8/2033"));

		validDates.add(Arrays.asList("3/5/1999",  "31", "2/4/1999"));
		validDates.add(Arrays.asList("12/5/1999", "31", "11/4/1999"));
		validDates.add(Arrays.asList("31/5/1999", "31", "30/4/1999"));
		
		// Subtrai tamanho do mês + 1 para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList("4/2/1599",  "29", "6/1/1599"));
		validDates.add(Arrays.asList("13/2/1599", "29", "15/1/1599"));
		validDates.add(Arrays.asList("28/2/1599", "29", "30/1/1599"));

		validDates.add(Arrays.asList("4/2/1808",  "30", "5/1/1808"));
		validDates.add(Arrays.asList("13/2/1808", "30", "14/1/1808"));
		validDates.add(Arrays.asList("29/2/1808", "30", "30/1/1808"));

		validDates.add(Arrays.asList("4/11/2000",  "31", "4/10/2000"));
		validDates.add(Arrays.asList("13/11/2000", "31", "13/10/2000"));
		validDates.add(Arrays.asList("30/11/2000", "31", "30/10/2000"));

		validDates.add(Arrays.asList("4/7/1903",  "32", "2/6/1903"));
		validDates.add(Arrays.asList("13/7/1903", "32", "11/6/1903"));
		validDates.add(Arrays.asList("31/7/1903", "32", "29/6/1903"));
		
		// Subtrai meio ano para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList("5/2/1737",  "182", "7/8/1736"));
		validDates.add(Arrays.asList("14/2/1737", "182", "16/8/1736"));
		validDates.add(Arrays.asList("28/2/1737", "182", "30/8/1736"));

		validDates.add(Arrays.asList("5/2/1888",  "183", "6/8/1887"));
		validDates.add(Arrays.asList("14/2/1888", "183", "15/8/1887"));
		validDates.add(Arrays.asList("29/2/1888", "183", "30/8/1887"));

		validDates.add(Arrays.asList("5/4/2204",  "183", "5/10/2203"));
		validDates.add(Arrays.asList("14/4/2204", "183", "14/10/2203"));
		validDates.add(Arrays.asList("30/4/2204", "183", "30/10/2203"));

		validDates.add(Arrays.asList("5/8/1959",  "182", "4/2/1959"));
		validDates.add(Arrays.asList("14/8/1959", "182", "13/2/1959"));
		validDates.add(Arrays.asList("31/8/1959", "182", "2/3/1959"));
		
		// Soma tamanho do ano para mês de 28, 29, 30, 31, no início, no meio e no fim do mês
		validDates.add(Arrays.asList("6/2/1597",  "365", "7/2/1596"));
		validDates.add(Arrays.asList("15/2/1597", "365", "16/2/1596"));
		validDates.add(Arrays.asList("28/2/1597", "365", "29/2/1596"));

		validDates.add(Arrays.asList("6/2/1644",  "366", "5/2/1643"));
		validDates.add(Arrays.asList("15/2/1644", "366", "14/2/1643"));
		validDates.add(Arrays.asList("29/2/1644", "366", "28/2/1643"));

		validDates.add(Arrays.asList("6/9/8000",  "366", "6/9/7999"));
		validDates.add(Arrays.asList("15/9/8000", "366", "15/9/7999"));
		validDates.add(Arrays.asList("30/9/8000", "366", "30/9/7999"));

		validDates.add(Arrays.asList("6/10/1715",  "365", "6/10/1714"));
		validDates.add(Arrays.asList("15/10/1715", "365", "15/10/1714"));
		validDates.add(Arrays.asList("31/10/1715", "365", "31/10/1714"));
		
		return validDates;
	}
	
	private Map<String, String> getValidDatesAsStrings() {
		Map<String, String> invalidDates = new HashMap<String, String>();

		invalidDates.put("01/1/1583", "1 January 1583");
		invalidDates.put("1/11/1799", "1 November 1799");
		invalidDates.put("04/12/2000", "4 December 2000");

		invalidDates.put("19/06/1800", "19 June 1800");
		invalidDates.put("30/6/6000", "30 June 6000");

		invalidDates.put("29/8/2222", "29 August 2222");
		invalidDates.put("30/8/2222", "30 August 2222");
		invalidDates.put("31/8/2222", "31 August 2222");
		
		return invalidDates;
	}
}
