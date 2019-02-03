/*
 * Ritesh Thapa CS-250
 * Assigment 2C
 *
 * Folios class includes stockportfolio, mutualportfolio and bondportfolio
 * 
 */
public class Folios implements java.io.Serializable {
	
	// holds stockport_folio, mutualfund_portfolio, and bond_portfolio
	private StockPortfolio stockPortfolio;
	private MututalFundPortfolio mututalFundPortfolio;
	private BondPortfolio bondPortfolio;
	
	// constructor
	public Folios(StockPortfolio s,MututalFundPortfolio m,BondPortfolio b)
	{
		this.stockPortfolio = s;
		this.mututalFundPortfolio = m;
		this.bondPortfolio = b;
	}

	// toString method to return the stock_portfolios, mutualfund_portfolios and bond_portfolios
	public String toString()
	{
		return "PortFolios: " 
				+"\n"+stockPortfolio.toString()
				+"\n"+mututalFundPortfolio.toString() 
				+"\n"+bondPortfolio.toString();
	}

	public StockPortfolio getStockPortfolio() {
		return stockPortfolio;
	}

	public MututalFundPortfolio getMututalFundPortfolio() {
		return mututalFundPortfolio;
	}

	public BondPortfolio getBondPortfolio() {
		return bondPortfolio;
	}
	
	
}
