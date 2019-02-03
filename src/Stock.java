

/*
 * Ritesh Thapa CS-250
 * Assignment2C
 */
public class Stock extends Assets implements java.io.Serializable {
	
	//constructor 
	public Stock(String name, double v, int a){
		this.name = name;
		this.currentValue = v; 
		this.numberAvailable = a;
	}
	
	protected double taxRate = 0.3;// 30% taxRate

	// toString method to return the name, currentValue, and numberAvailable
	public String toString(){
		return "Stock: "+ name +"\tCurrent Value: $"+ currentValue + "\tNumbers Available: " + numberAvailable; 
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
