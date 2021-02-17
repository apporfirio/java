package edu.test;

import org.junit.Test;

import edu.BasicStringCalculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicStringCalculatorTest {
	
	/*
	 	Tarefa
	 	
	 		Dada uma string formatada, conforme especificado abaixo, calcule a soma
	 		dos números na string.
	  	
	  		Uma string vazia resulta em 0.
	  		Uma string com um único número resulta no número.
	  		Uma string com dois ou mais números separados um do outro por uma vírgula ou por uma quebra de linha resulta na soma dos números.
	  		Uma string com pelo menos um número negativo resulta em erro.
	 */
	
	@Test(expected = BasicStringCalculator.InvalidCharacterException.class)
	public void inputCannotHaveNegativeNumbers() {
		BasicStringCalculator basicStringCalculator = new BasicStringCalculator();
		
		basicStringCalculator.add("-3,44.44");
	}
	
	@Test(expected = BasicStringCalculator.InvalidNumberException.class)
	public void inputCannotHaveInvalidNumbers() {
		BasicStringCalculator basicStringCalculator = new BasicStringCalculator();

		for (String stringWithInvalidNumber : getStringsWithInvalidNumbers()) {
			basicStringCalculator.add(stringWithInvalidNumber);
		}
		
	}
	
	@Test
	public void canCalculateEmptyString() {
		BasicStringCalculator basicStringCalculator = new BasicStringCalculator();
		
		assertEquals(Double.valueOf(0), basicStringCalculator.add(""));
	}

	@Test
	public void canCalculateSingleValueString() {
		BasicStringCalculator basicStringCalculator = new BasicStringCalculator();

		assertEquals(Double.valueOf(17893), basicStringCalculator.add("17893"));
		assertEquals(Double.valueOf(13.3452), basicStringCalculator.add("13.3452"));
	}
	
	@Test
	public void canCalculateDoubleValuedString() {
		String commaSeparatedInput = "17893,13.3452";
		String newlineSeparatedInput = "13.3452\n17893";
		Double sum = 17893 + 13.3452 ;
		
		BasicStringCalculator basicStringCalculator = new BasicStringCalculator();
		
		assertEquals(sum, basicStringCalculator.add(commaSeparatedInput));
		assertEquals(sum, basicStringCalculator.add(newlineSeparatedInput));
	}
	
	@Test
	public void canCalculateMultipleValuedString() {
		for (Map.Entry<String, Double> multipleValuedStringAndResult : getMultipleValuedStringsAndResults().entrySet()) {
			BasicStringCalculator basicStringCalculator = new BasicStringCalculator();

			assertEquals(multipleValuedStringAndResult.getValue(), basicStringCalculator.add(multipleValuedStringAndResult.getKey()));
		}
	}
	
	private List<String> getStringsWithInvalidNumbers() {
		return Arrays.asList("333,.044", "5.4.3,0", "1, 3..14, .99");
	}
	
	private Map<String, Double> getMultipleValuedStringsAndResults() {
		Map<String, Double> multipleValuedStringsAndsResults = new HashMap<String, Double>();
		
		multipleValuedStringsAndsResults.put("17893,13.3452,90.90", 17893 + 13.3452 + 90.90);
		multipleValuedStringsAndsResults.put("17893\n13.3452\n90.90", 17893 + 13.3452 + 90.90);
		multipleValuedStringsAndsResults.put("17893\n13.3452,90.90,55\n3.14", 17893 + 13.3452 + 90.90 + 55 + 3.14);
		multipleValuedStringsAndsResults.put("17893,13.3452\n90.90\n55,3.14", 17893 + 13.3452 + 90.90 + 55 + 3.14);

		return multipleValuedStringsAndsResults;
	}
	
}
