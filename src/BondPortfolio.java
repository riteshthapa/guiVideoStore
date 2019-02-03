/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * This is a BondPortfolio that inherits Portfolio and has an array of Bond, and number held for each bond.
 */
public class BondPortfolio extends Portfolio implements java.io.Serializable {
	
	private Bond[] myBonds; // bond array
	private Integer[] numberHeld; // an array of number held for each bond
	
	// constructor 
	public BondPortfolio(Bond[] bonds, Integer[] numberHeld)
	{
		this.myBonds = bonds;
		this.numberHeld = numberHeld;
		calculateCurrentValue();
	}
	
	// toString method
	public String toString() {
		String outputString = "Bonds Owned:\n";
 		for (int i= 0; i < myBonds.length; i++){
 			outputString += "\t"+myBonds[i].name+": "+ numberHeld[i] + "\n";
 		}
 		return outputString;
	}

	// bond calculateCurrentValue method 
	@Override
	public void calculateCurrentValue() {
		double value = 0.0;
		for(int i=0; i < myBonds.length; i++) {
			value += myBonds[i].currentValue * numberHeld[i];
		}
		this.currentValue = value;
	}

	public Bond[] getBonds() {
		return myBonds;
	}

	public Integer[] getNumberHeld() {
		return numberHeld;
	}
}