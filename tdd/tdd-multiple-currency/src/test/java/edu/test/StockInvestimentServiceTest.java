package edu.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.StockInvestimentService;
import edu.StockInvestiment;

public class StockInvestimentServiceTest {
	/*
	 	Tarefa
	 	
	 		Considere uma tabela 'Investimento' com colunas Empresa (varchar), 
	 		Ações (integer), Preço (decimal com 2 casas), e Moeda (varchar). 
	 		
	 		Considere também uma tabela 'CambioDolar' com colunas Moeda (varchar) 
	 		e Taxa (decimal com 2 casas).
	 		
	 		Calcule a soma total em dólar de todos os investimentos registrados.
	 		

	 		Exemplo
	 		
	 		| Empresa | Ações | Preço  | Moeda |
	 		|  ABC	  | 1300  | 25.13  | USD   |
	 		|  XYZ	  | 700   | 150.40 | LIB   |
	 		|  OLP	  | 331   | 57.90  | BRL   |

			| Moeda | Taxa |
			| USD	| 1.00 |
			| LIB	| 1.31 |
			| BRL   | 0.02 |
	*/
	
	@Test
	public void canCalculateTotalShareValueInDollarPerInvestiment() {
		for (Map.Entry<StockInvestiment, Double> sample : getInvestimentSamples().entrySet()) {
			assertEquals(
					sample.getValue(), 
					StockInvestimentService.calculateTotalShareValueInDollar(sample.getKey()), 
					0.01
			);
		}
	}
	
	@Test
	public void canCalculateTotalInvestimentValueInDollar() {
		assertEquals(
				getTotalInvestimentValueInDollarSample(), 
				StockInvestimentService.calculateTotalInvestimentValueInDollar(getInvestimentSamples().keySet()), 
				0.01
		);
	}
	
	private Map<StockInvestiment, Double> getInvestimentSamples() {
		Map<StockInvestiment, Double> samples = new HashMap<StockInvestiment, Double>();
		
		samples.put(
				new StockInvestiment.StockInvestimentBuilder()
				.companyName("ABC")
				.shareUnits(1300)
				.shareUnitPrice(25.13)
				.currencyToDolarRatio(1.00)
				.build(),
				1300 * 25.13 * 1.00
		);
		
		samples.put(
				new StockInvestiment.StockInvestimentBuilder()
				.companyName("XYZ")
				.shareUnits(700)
				.shareUnitPrice(150.40)
				.currencyToDolarRatio(1.31)
				.build(),
				700 * 150.40 * 1.31
		);
		
		samples.put(
				new StockInvestiment.StockInvestimentBuilder()
				.companyName("OLP")
				.shareUnits(331)
				.shareUnitPrice(57.90)
				.currencyToDolarRatio(0.02)
				.build(),
				331 * 57.90 * 0.02
		);
		
		return samples;
	}
	
	private double getTotalInvestimentValueInDollarSample() {
		double sample = 0;
		
		for (Double totalShareValueInDollar : getInvestimentSamples().values()) {
			sample += totalShareValueInDollar;
		}
		
		return sample;
	}
}
