/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * This is a customer account class that has customer_name, customer_id, cash_in_hand and folios.
 * 
 */
public class CustomerAccount extends Account implements java.io.Serializable {

	protected double cashInHand;
	protected Folios portfolios;
	protected double assetValue;
	
	// customerAccount constructor 
	public CustomerAccount(int id, String Name, double cashInHand, Folios portfolios) {
		this.id = id;
		this.accountHolderName = Name;
		this.cashInHand = cashInHand;
		this.portfolios = portfolios;
		calculateAssetValue();
	}

	// toString method
	public String toString()
	{
		return "Customer Id : "+id+"\n"+accountHolderName + "\nCash in Hand: $ " + cashInHand +"\n"
								 + portfolios.toString() +"\n"
								 + "Current Value : \n"
								 + "\tStock Portfolio : "+portfolios.getStockPortfolio().currentValue+"\n"
								 + "\tMutualFund Portfolio : "+portfolios.getMututalFundPortfolio().currentValue+"\n"
								 + "\tBond Portfolio : "+portfolios.getBondPortfolio().currentValue+"\n\n"
								 + "Total Asset Value: $ " + assetValue +"\n\n";
	}
	
	// updates assetValue
	public void calculateAssetValue() {
		this.assetValue = portfolios.getBondPortfolio().currentValue 
							+ portfolios.getMututalFundPortfolio().currentValue
							+ portfolios.getStockPortfolio().currentValue;
	}
	
	// calculates payable tax and tries to reduce from cash in hand
	// returns true if action succeeds
	// returns false if failed, that is if cash in hand is insufficient
	public boolean payTax() {
		double totalTaxForBonds = 0.0;
		Bond[] bonds = portfolios.getBondPortfolio().getBonds();
		Integer[] bondsHeld = portfolios.getBondPortfolio().getNumberHeld();
		for(int i = 0; i<bonds.length; i++) {
			totalTaxForBonds += bonds[i].calculateTax() * bondsHeld[i];
		}

		double totalTaxForMF = 0.0;
		MutualFund[] funds = portfolios.getMututalFundPortfolio().getMutualFunds();
		Integer[] fundsHeld = portfolios.getMututalFundPortfolio().getNumberHeld();
		for(int i = 0; i<funds.length; i++) {
			totalTaxForMF += funds[i].calculateTax() * fundsHeld[i];
		}

		double totalTaxForStocks = 0.0;
		Stock[] stocks = portfolios.getStockPortfolio().getStocks();
		Integer[] stocksHeld = portfolios.getStockPortfolio().getNumberHeld();
		for(int i = 0; i<stocks.length; i++) {
			totalTaxForStocks += stocks[i].calculateTax() * stocksHeld[i];
		}
		
		double totalTax = totalTaxForBonds + totalTaxForMF + totalTaxForStocks;
		
		System.out.println("total payable tax : "+totalTax);

		if(cashInHand >= totalTax) {
			cashInHand -= totalTax;
			return true;
		}else {
			return false;
		}
		
	}
}
