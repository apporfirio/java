package edu.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import edu.LeapYearValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LeapYearValidatorTest {

	 /*
		Tarefa
		 	
		 	Implementar um método LeapYearValidation validate(int year) que retorne um resultado composto contendo um
		 	atributo booleano que diga se o ano informado é bissexto ou não e um atributo String que faça o
		 	mesmo mas de modo textual, contendo a String "Leap year" quando o ano informado for bissexto e 
		 	quando o não for, contendo uma String no formato "Not leap year: <reason>", onde <reason>  
		 	é a explicacao de porquê o ano não é bissexto. 
		 	
		 	O método deve retornar uma IllegalArgumentException quando o ano informado for anterior a 1582,
		 	que é a data de início do Calendário Gregoriano (nosso calendário atual).
	 	
	 	Ano bissexto
	 	
	 		 No Calendário Gregoriano,
	 			 	
		 	 um ano é bissexto quando é múltiplo de 4, exceto quando também é múltiplo de 100 sem ser múltiplo de 400;
		 	 
			 em um ano bissexto, acrescenta-se um dia ao total anual de dias, sendo o dia adicional considerado como 29 de fevereiro;

		Referências
		
			https://www.mathsisfun.com/leap-years.html
			https://scienceworld.wolfram.com/astronomy/LeapYear.html
			https://pt.wikipedia.org/wiki/Ano_bissexto
	 */
	
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotValidateNegativeYears() {
		getValidator().validate(-2000);
	}
	
	@Test
	public void canValidateNonDivisibleBy4Years() {
		for (Map.Entry<Integer, LeapYearValidator.LeapYearValidation> sample : getNonDivisibleBy4Sample().entrySet()) {
			
			LeapYearValidator.LeapYearValidation expectedResult = sample.getValue();
			LeapYearValidator.LeapYearValidation result = getValidator().validate(sample.getKey());
			
			assertTrue(result.equals(expectedResult));
		}
	}
	
	@Test
	public void canValidateDivisibleBy4And100ButNotBy400Years() {
		for (Map.Entry<Integer, LeapYearValidator.LeapYearValidation> sample : getDivisibleBy4AndBy100ButNotBy400Sample().entrySet()) {
			
			LeapYearValidator.LeapYearValidation expectedResult = sample.getValue();
			LeapYearValidator.LeapYearValidation result = getValidator().validate(sample.getKey());
			
			assertTrue(result.equals(expectedResult));
		}
	}
	
	@Test
	public void canValidateLeapYears() {
		for (Map.Entry<Integer, LeapYearValidator.LeapYearValidation> sample : getLeapYearSample().entrySet()) {
			
			LeapYearValidator.LeapYearValidation expectedResult = sample.getValue();
			LeapYearValidator.LeapYearValidation result = getValidator().validate(sample.getKey());
			
			assertTrue(result.equals(expectedResult));
		}
	}
	
	private LeapYearValidator getValidator() {
		return new LeapYearValidator();
	}
	
	private Map<Integer, LeapYearValidator.LeapYearValidation> getLeapYearSample() {
		Map<Integer, LeapYearValidator.LeapYearValidation> leapYearSample = new HashMap<Integer, LeapYearValidator.LeapYearValidation>();

		for (Integer leapYear : getLeapYears()) {
			leapYearSample.put(
					leapYear,
					new LeapYearValidator.LeapYearValidation(true, "Leap year")
			);
		}
		
		return leapYearSample;
	}

	
	private Map<Integer, LeapYearValidator.LeapYearValidation> getNonDivisibleBy4Sample() {
		Map<Integer, LeapYearValidator.LeapYearValidation> nonLeapYearSample = new HashMap<Integer, LeapYearValidator.LeapYearValidation>();

		for (Integer nonDivisibleBy4Year : getNonDivisibleBy4Years()) {
			nonLeapYearSample.put(
					nonDivisibleBy4Year, 
					new LeapYearValidator.LeapYearValidation(false, "Not leap year: it is not divisible by 4")
			);
		}

		return nonLeapYearSample;
	}

	
	private Map<Integer, LeapYearValidator.LeapYearValidation> getDivisibleBy4AndBy100ButNotBy400Sample() {
		Map<Integer, LeapYearValidator.LeapYearValidation> nonLeapYearSample = new HashMap<Integer, LeapYearValidator.LeapYearValidation>();

		for (Integer divisibleBy4AndBy100ButNotBy400Year : getDivisibleBy4AndBy100ButNotBy400Years()) {
			nonLeapYearSample.put(
					divisibleBy4AndBy100ButNotBy400Year, 
					new LeapYearValidator.LeapYearValidation(false, "Not leap year: it is divisible by 4 and by 100 but not by 400")
			);
		}

		return nonLeapYearSample;
	}
	
	
	private List<Integer> getLeapYears() {
		return Arrays.asList(1584, 1600, 2000, 2016, 2020, 2400);
	}
	
	private List<Integer> getNonDivisibleBy4Years() {
		return Arrays.asList(1582, 1601, 1733, 2011);
	}
	
	private List<Integer> getDivisibleBy4AndBy100ButNotBy400Years() {
		return Arrays.asList(1700, 2100, 3700, 5000);
	}
}
