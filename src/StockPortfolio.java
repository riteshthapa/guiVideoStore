

/*
 * Ritesh Thapa CS-250
 * Assignment2C
 */
public class StockPortfolio extends Portfolio implements java.io.Serializable{
 	
	// creating an array of size 4 
	private Stock[] stocks;
	private Integer[] numberHeld;
	
 	public StockPortfolio(Stock[] stocks, Integer[] numberHeld) {
 		this.stocks = stocks;
 		this.numberHeld = numberHeld;
 		calculateCurrentValue();
 	}
 	//toString method to output the stock owned
 	public String toString() {
 		String outputString = "Stocks Owned:\n";
 		for (int i= 0; i < stocks.length; i++){
 			outputString += "\t" + stocks[i].name + " : "+ numberHeld[i] + "\n";
 		}
 		return outputString;
 	}
	@Override
	public void calculateCurrentValue() {
		double value = 0.0;
		for(int i=0; i < stocks.length; i++) {
			value += stocks[i].currentValue * numberHeld[i];
		}
		this.currentValue = value;
	}
	
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public void setNumberHeld(Integer[] numberHeld) {
		this.numberHeld = numberHeld;
	}
	public Stock[] getStocks() {
		return stocks;
	}
	public Integer[] getNumberHeld() {
		return numberHeld;
	}
	
}
