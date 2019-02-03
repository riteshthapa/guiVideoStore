/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * This is a Assets class which is also a parent class.
 * 
 */
public abstract class Assets {
	
	// data members 
	protected String name;
	protected double currentValue;
	protected int numberAvailable;
	
	public abstract double calculateTax(); // abstract calculateTax method
	
	public abstract double calculateValue(); // abstract calculateValue method
}
