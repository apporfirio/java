package edu;

public class StockInvestiment {

	private StockInvestimentState state;
	
	private StockInvestiment() {};
	
	private StockInvestiment(StockInvestimentState state) {
		this.state = state;
	}

	public String getCompanyName() {
		return state.getCompanyName();
	}

	public Integer getShareUnits() {
		return state.getShareUnits();
	}

	public Double getShareUnitPrice() {
		return state.getShareUnitPrice();
	}

	public Double getCurrenyToDolarRatio() {
		return state.getCurrenyToDolarRatio();
	}

	public static class StockInvestimentBuilder {
		private StockInvestimentState state;
		
		public StockInvestimentBuilder() {
			this.state = new StockInvestimentState();
		}
		
		public StockInvestimentBuilder companyName(String companyNameToSet) {
			state.setCompanyName(companyNameToSet);
			return this;
		}
		
		public StockInvestimentBuilder shareUnits(Integer shareUnitsToSet) {
			state.setShareUnits(shareUnitsToSet);
			return this;
		}
		
		public StockInvestimentBuilder shareUnitPrice(Double shareUnitPriceToSet) {
			state.setShareUnitPrice(shareUnitPriceToSet);
			return this;
		}
		
		public StockInvestimentBuilder currencyToDolarRatio(Double currencyToDolarRatioToSet) {
			state.setCurrencyToDolarRatio(currencyToDolarRatioToSet);
			return this;
		}
		
		public StockInvestiment build() {
			return new StockInvestiment(state);
		}
	}
	
	private static class StockInvestimentState {
		private String companyName;
		private Integer shareUnits;
		private Double shareUnitPrice;
		private Double currencyToDolarRatio;
		
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
		public Integer getShareUnits() {
			return shareUnits;
		}
		public void setShareUnits(Integer shareUnits) {
			this.shareUnits = shareUnits;
		}
		
		public Double getShareUnitPrice() {
			return shareUnitPrice;
		}
		public void setShareUnitPrice(Double shareUnitPrice) {
			this.shareUnitPrice = shareUnitPrice;
		}
		
		public Double getCurrenyToDolarRatio() {
			return currencyToDolarRatio;
		}
		public void setCurrencyToDolarRatio(Double currencyToDolarRatio) {
			this.currencyToDolarRatio = currencyToDolarRatio;
		}
	}
	
	
}
