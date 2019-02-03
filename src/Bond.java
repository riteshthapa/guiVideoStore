/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * This is a Bond class that extends Assets and defines the methods declared in Asset. 
 * 
 */
public class Bond extends Assets implements java.io.Serializable{
	
	protected double taxRate = 0.1;// 10% taxRate
	
	// constructor 
	public Bond(String name, double v, int n){
		this.name = name;
		this.currentValue = v;
		this.numberAvailable = n;
	}
	
	// toString method
	public String toString(){
		return "Bond Name: " + name+ "\tCurrent Value: $"+ currentValue + "\tNumbers Available: " + numberAvailable; 
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
