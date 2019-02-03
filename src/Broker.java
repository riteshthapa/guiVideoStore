import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
/*
 * Ritesh Thapa 
 * CS 250
 * 
 * Assignment2C
 * 
 * 11/19/2017
 * 
 * This is Broker class the creates and holds an array of the customerAccount stock, mutualFund and Bond.
 * 
 * Updated--
 * Maintains the updated information in its arrays of customer, stock, mutualFund and Bond.
 * 
 */
public class Broker implements java.io.Serializable {
	
	private Stock[] myStocks;
	private MutualFund[] myMutualFunds;
	private Bond[] myBonds = new Bond[4];
	private CustomerAccount[] myCustomers = new CustomerAccount[2];
	
	public Broker() throws FileNotFoundException {
		fillBonds(new File("BD.dat"));
		fillMutualFunds(new File("MF.dat"));
		fillStocks(new File("STK.dat"));
		fillCustomers(new File("CA.dat"));
	}

	//fills myStocks[] from file
	private void fillStocks(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int n = scanner.nextInt();			// extracts number of stocks available in file
		myStocks = new Stock[n];			// initializes myStocks with number of stocks available in file
		for (int i= 0; i<n; i++){
			myStocks[i] = new Stock(scanner.next().trim(),scanner.nextDouble(),scanner.nextInt());
		}
		scanner.close();
	}
	
	//fills myBonds[] from file
	private void fillBonds(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		for (int i= 0; i<4; i++){
			myBonds[i] = new Bond(scanner.next().trim(),scanner.nextDouble(),scanner.nextInt());
		}
		scanner.close();
	}

	//fills myMutualFunds[] from file
	private void fillMutualFunds(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int n = scanner.nextInt();			// extracts number of funds available in file
		myMutualFunds = new MutualFund[n];			// initializes myMutualFunds with number of funds available in file
		for (int i= 0; i<n; i++){
			myMutualFunds[i] = new MutualFund(scanner.next().trim(),scanner.nextDouble(),scanner.nextInt());
		}
		scanner.close();
	}
	
	//fills myCustomers[] from file
	private void fillCustomers(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		for (int i= 0; i<2; i++){
			// extracts customer informations
			String customerName = (scanner.next()+" "+scanner.next()).trim();
			int customerId = scanner.nextInt();
			double cashInHand = scanner.nextDouble();
			
			// extracts number of stock portfolios held
			Integer[] stocksHeld = new Integer[myStocks.length];
			for(int j = 0; j<myStocks.length; j++) {
				stocksHeld[j] = scanner.nextInt();
			}
			StockPortfolio sp = new StockPortfolio(myStocks, stocksHeld);
			
			// extracts number of mutualfund portfolios held 
			Integer[] mfHeld = new Integer[myMutualFunds.length];
			for(int j = 0; j<myMutualFunds.length; j++) {
				mfHeld[j] = scanner.nextInt();
			}
			MututalFundPortfolio mp = new MututalFundPortfolio(myMutualFunds, mfHeld);
			
			// extracts number of bonds portfolios held 
			Integer[] bondsHeld = new Integer[myBonds.length];
			for(int j = 0; j<myBonds.length; j++) {
				bondsHeld[j] = scanner.nextInt();
			}
			BondPortfolio bp = new BondPortfolio(myBonds, bondsHeld);
			
			//creates folios of a customer
			Folios folios = new Folios(sp, mp, bp);
			
			myCustomers[i] = new CustomerAccount(customerId, customerName, cashInHand, folios);
		}
		scanner.close();
	}

	// returns array index of customer with provided id 
	//returns -1, if not available
	public int searchCustomerById(String customerId) throws InvalidCustomerIDException {
		Integer id = null;
		try {
			id = Integer.parseInt(customerId);
			if(myCustomers.length == 0) {	 // if no customer available
				throw new InvalidCustomerIDException("No Customer Available");
			} else {
				int index = 0;
				while(index < myCustomers.length && myCustomers[(index)].id != id) {index++;}
				if(index == myCustomers.length) { // if customer id is not available 
					throw new InvalidCustomerIDException("Customer with id '"+id+"' not found!");
				}else {
					return index;
				}
			}
		}catch(NumberFormatException e) {
			throw new InvalidCustomerIDException("id should be a number");
		}
	}
	
	//pays tax of the customer
	//returns true if action succeeds
	//returns false if failed, that is if cash in hand is insufficient
	public boolean payTaxOfCustomer(int index) {
		CustomerAccount customer = myCustomers[index];
		boolean status = customer.payTax();
		myCustomers[index] = customer;
		return status;
	}
	
	// returns array index of Stock with provided name
	// returns -1, if not available
	public int searchStockByName(String stockName) throws InvalidAssetNameException {
		int index = 0;
		while(index < myStocks.length && !myStocks[(index)].name.equals(stockName)) {index++;}
		if(index == myStocks.length) {throw new InvalidAssetNameException("Stock with name '"+stockName+"' not found");}
		return index;
	}
	
	// returns array index of MutualFund with provided name
	// returns -1, if not available
	public int searchMutualFundByName(String fundName) throws InvalidAssetNameException {
		int index = 0;
		while(index < myMutualFunds.length && !myMutualFunds[(index)].name.equals(fundName)) {index++;}
		if(index == myMutualFunds.length) {throw new InvalidAssetNameException("Mutual Fund with name '"+fundName+"' not found");}
		return index;
	}

	
	// returns array index of Bond with provided name
	// returns -1, if not available
	public int searchBondByName(String bondName) throws InvalidAssetNameException {
		int index = 0;
		while(index < myBonds.length && !myBonds[(index)].name.equals(bondName)) {index++;}
		if(index == myBonds.length) {throw new InvalidAssetNameException("Bond with name '"+bondName+"' not found");}
		return index;
	}
	
	// sells stock of the customer
	public void sellStock(int customerIndex, int stockindex, int numberToSell) throws NotEnoughAssetToSellException {
		CustomerAccount customer = myCustomers[customerIndex];
		Stock[] stocks = customer.portfolios.getStockPortfolio().getStocks();
		Integer[] numberHeld = customer.portfolios.getStockPortfolio().getNumberHeld();
		if(numberHeld[stockindex] >= numberToSell) {
			double soldAmount = stocks[stockindex].currentValue * numberToSell;
			numberHeld[stockindex] -= numberToSell;
			myStocks[stockindex].numberAvailable += numberToSell;
			customer.cashInHand += soldAmount;
			customer.portfolios.getStockPortfolio().getNumberHeld()[stockindex] = numberHeld[stockindex];
			customer.portfolios.getStockPortfolio().calculateCurrentValue();
			customer.calculateAssetValue();
			myCustomers[customerIndex] = customer;
		}else {
			throw new NotEnoughAssetToSellException("Insufficient stocks held!\n"
					+ "Number of stocks held : " + numberHeld[stockindex]+"\n"
					+ "Number of stocks to sell : "+ numberToSell);
		}
	}
	
	// sells fund of the customer
	public void sellMutualFund(int customerIndex, int fundIndex, int numberToSell) throws NotEnoughAssetToSellException {
		CustomerAccount customer = myCustomers[customerIndex];
		MutualFund[] funds = customer.portfolios.getMututalFundPortfolio().getMutualFunds();
		Integer[] numberHeld = customer.portfolios.getMututalFundPortfolio().getNumberHeld();
		if(numberHeld[fundIndex] >= numberToSell) {
			double soldAmount = funds[fundIndex].currentValue * numberToSell;
			numberHeld[fundIndex] -= numberToSell;
			myMutualFunds[fundIndex].numberAvailable += numberToSell;
			customer.cashInHand += soldAmount;
			customer.portfolios.getMututalFundPortfolio().getNumberHeld()[fundIndex] = numberHeld[fundIndex];
			customer.portfolios.getMututalFundPortfolio().calculateCurrentValue();
			customer.calculateAssetValue();
			myCustomers[customerIndex] = customer;
		}else {
			throw new NotEnoughAssetToSellException("Insufficient mutual funds held!\n"
					+ "Number of mutual funds held : " + numberHeld[fundIndex]+"\n"
					+ "Number of mutual funds to sell : "+ numberToSell);
		}
	}
	
	// sells bond of the customer
	public void sellBond(int customerIndex, int bondIndex, int numberToSell) throws NotEnoughAssetToSellException {
		CustomerAccount customer = myCustomers[customerIndex];
		Bond[] bonds = customer.portfolios.getBondPortfolio().getBonds();
		Integer[] numberHeld = customer.portfolios.getBondPortfolio().getNumberHeld();
		if(numberHeld[bondIndex] >= numberToSell) {
			double soldAmount = bonds[bondIndex].currentValue * numberToSell;
			numberHeld[bondIndex] -= numberToSell;
			myBonds[bondIndex].numberAvailable += numberToSell;
			customer.cashInHand += soldAmount;
			customer.portfolios.getBondPortfolio().getNumberHeld()[bondIndex] = numberHeld[bondIndex];
			customer.portfolios.getBondPortfolio().calculateCurrentValue();
			customer.calculateAssetValue();
			myCustomers[customerIndex] = customer;
		}else {
			throw new NotEnoughAssetToSellException("Insufficient bonds held!\n"
					+ "Number of bonds held : " + numberHeld[bondIndex]+"\n"
					+ "Number of bonds to sell : "+ numberToSell);
		}
	}
	
	// buys stock for the customer
	public void buyStock(int customerIndex, int stockindex, int numberToBuy) throws NotEnoughAssetToBuyException, NotEnoughCashInHandToBuyException {
		CustomerAccount customer = myCustomers[customerIndex];
		Integer[] numberHeld = customer.portfolios.getStockPortfolio().getNumberHeld();
		if(myStocks[stockindex].numberAvailable >= numberToBuy) {
			double purchaseAmount = myStocks[stockindex].currentValue * numberToBuy;
			if(customer.cashInHand >= purchaseAmount) {
				myStocks[stockindex].numberAvailable -= numberToBuy;
				numberHeld[stockindex] += numberToBuy;
				customer.cashInHand -= purchaseAmount;
				customer.portfolios.getStockPortfolio().getNumberHeld()[stockindex] = numberHeld[stockindex];
				customer.portfolios.getStockPortfolio().calculateCurrentValue();
				customer.calculateAssetValue();
				myCustomers[customerIndex] = customer;
			}else {
				throw new NotEnoughCashInHandToBuyException("Not enough cash to buy stocks\n"
						+ "Available cash in hand : "+ customer.cashInHand+"\n"
						+ "Required : " + purchaseAmount);
			}
		}else {
			throw new NotEnoughAssetToBuyException("Insufficient stocks available!\n"
					+ "Number of stocks available : " + myStocks[stockindex].numberAvailable+"\n"
					+ "Number of stocks to buy : "+ numberToBuy);
		}
	}
	
	// buys fund for the customer
	public void buyMutualFund(int customerIndex, int fundIndex, int numberToBuy) throws NotEnoughCashInHandToBuyException, NotEnoughAssetToBuyException {
		CustomerAccount customer = myCustomers[customerIndex];
		Integer[] numberHeld = customer.portfolios.getMututalFundPortfolio().getNumberHeld();
		if(myMutualFunds[fundIndex].numberAvailable >= numberToBuy) {
			double purchaseAmount = myMutualFunds[fundIndex].currentValue * numberToBuy;
			if(customer.cashInHand >= purchaseAmount) {
				myMutualFunds[fundIndex].numberAvailable -= numberToBuy;
				numberHeld[fundIndex] += numberToBuy;
				customer.cashInHand -= purchaseAmount;
				customer.portfolios.getMututalFundPortfolio().getNumberHeld()[fundIndex] = numberHeld[fundIndex];
				customer.portfolios.getMututalFundPortfolio().calculateCurrentValue();
				customer.calculateAssetValue();
				myCustomers[customerIndex] = customer;
			}else {
				throw new NotEnoughCashInHandToBuyException("Not enough cash to buy mutual funds\n"
						+ "Available cash in hand : "+ customer.cashInHand+"\n"
						+ "Required : " + purchaseAmount);
			}
		}else {
			throw new NotEnoughAssetToBuyException("Insufficient mutual funds available!\n"
					+ "Number of mutual funds available : " + myMutualFunds[fundIndex].numberAvailable+"\n"
					+ "Number of mutual funds to buy : "+ numberToBuy);
		}
	}
	
	// buys bond for the customer
	public void buyBond(int customerIndex, int bondIndex, int numberToBuy) throws NotEnoughCashInHandToBuyException, NotEnoughAssetToBuyException {
		CustomerAccount customer = myCustomers[customerIndex];
		Integer[] numberHeld = customer.portfolios.getBondPortfolio().getNumberHeld();
		if(myBonds[bondIndex].numberAvailable >= numberToBuy) {
			double purchaseAmount = myBonds[bondIndex].currentValue * numberToBuy;
			if(customer.cashInHand >= purchaseAmount) {
				myBonds[bondIndex].numberAvailable -= numberToBuy;
				numberHeld[bondIndex] += numberToBuy;
				customer.cashInHand -= purchaseAmount;
				customer.portfolios.getBondPortfolio().getNumberHeld()[bondIndex] = numberHeld[bondIndex];
				customer.portfolios.getBondPortfolio().calculateCurrentValue();
				customer.calculateAssetValue();
				myCustomers[customerIndex] = customer;
			}else {
				throw new NotEnoughCashInHandToBuyException("Not enough cash to buy bonds\n"
						+ "Available cash in hand : "+ customer.cashInHand+"\n"
						+ "Required : " + purchaseAmount);
			}
		}else {
			throw new NotEnoughAssetToBuyException("Insufficient bonds available!\n"
					+ "Number of bonds available : " + myBonds[bondIndex].numberAvailable+"\n"
					+ "Number of bonds to buy : "+ numberToBuy);
		}
	}
	
	// updates data in files 
	public void updateFile() {
		try {
			updateStockFile();
			updateMFFile();
			updateBondFile();
			updateCAFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// updates stocks in STK.dat file
	public void updateStockFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream("STK.dat"));
		writer.println(myStocks.length);
		for(Stock stock: myStocks) {
			writer.print(stock.name);
			writer.print("\t\t");
			writer.print(stock.currentValue);
			writer.print("\t\t");
			writer.println(stock.numberAvailable);
		}
		writer.close();
	}
	
	// updates mutualFunds in MF.dat file
	public void updateMFFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream("MF.dat"));
		writer.println(myMutualFunds.length);
		for(MutualFund fund: myMutualFunds) {
			writer.print(fund.name);
			writer.print("\t\t");
			writer.print(fund.currentValue);
			writer.print("\t\t");
			writer.println(fund.numberAvailable);
		}
		writer.close();
	}
	
	// updates bonds in BD.dat file
	public void updateBondFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream("BD.dat"));
		for(Bond bond: myBonds) {
			writer.print(bond.name);
			writer.print("\t\t");
			writer.print(bond.currentValue);
			writer.print("\t\t");
			writer.println(bond.numberAvailable);
		}
		writer.close();
	}
	
	// updates customerAccounts in CA.dat file
	public void updateCAFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream("CA.dat"));
		for(CustomerAccount customer: myCustomers) {
			writer.print(customer.accountHolderName);
			writer.print("\t\t");
			writer.print(customer.id);
			writer.print("\t\t");
			writer.println(customer.cashInHand);
			for(Integer numberHeld : customer.portfolios.getStockPortfolio().getNumberHeld()) {
				writer.print(numberHeld);
				writer.print("\t");
			}
			writer.println();
			for(Integer numberHeld : customer.portfolios.getMututalFundPortfolio().getNumberHeld()) {
				writer.print(numberHeld);
				writer.print("\t");
			}
			writer.println();
			for(Integer numberHeld : customer.portfolios.getBondPortfolio().getNumberHeld()) {
				writer.print(numberHeld);
				writer.print("\t");
			}
			writer.println();
		}
		writer.close();
	}
	
	public Stock[] getStocks() {
		return myStocks;
	}

	public MutualFund[] getMutualFunds() {
		return myMutualFunds;
	}

	public Bond[] getBonds() {
		return myBonds;
	}

	public CustomerAccount[] getCustomers() {
		return myCustomers;
	}
}