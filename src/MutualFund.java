/*
 * Ritesh Thapa CS-250
 * Assignment2C
 * 
 * This is a MutualFund class that inherits Assets and defines the methods declared in Asset.
 */
public class MutualFund extends Assets implements java.io.Serializable {
	
	protected double taxRate = 0.2;// 20% taxRate
	
	// constructor 
	public MutualFund(String name, double value, int num)
	{
		this.name = name;
		this.currentValue = value;
		this.numberAvailable = num;
	}
		
	//toString Method to return the name, currentValue and numberAvailable
	public String toString(){
		
		return "Mutual Fund: "+ name +"\tCurrent Value: $"+ currentValue + "\tNumbers Avaibale: " + numberAvailable; 
	}

	@Override
	public double calculateTax() {
		return currentValue * taxRate;
	}

	@Override
	public double calculateValue() {
		return currentValue * numberAvailable;
	}

}
