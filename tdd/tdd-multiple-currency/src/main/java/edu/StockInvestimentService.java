package edu;

import java.util.Collection;

public class StockInvestimentService {

	public static double calculateTotalShareValueInDollar(StockInvestiment stockInvestiment) {
		return stockInvestiment.getShareUnits() * stockInvestiment.getShareUnitPrice() * stockInvestiment.getCurrenyToDolarRatio();
	}
	
	public static double calculateTotalInvestimentValueInDollar(Collection<StockInvestiment> stockInvestiments) {
		double total = 0;
		
		for (StockInvestiment stockInvestiment : stockInvestiments) {
			total += calculateTotalShareValueInDollar(stockInvestiment);
		}
		
		return total;
	}
	
}
