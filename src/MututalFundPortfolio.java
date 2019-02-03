/*
 * Ritesh Thapa CS-250
 * Assignment2C
 */
public class MututalFundPortfolio extends Portfolio implements java.io.Serializable{
	
	private MutualFund[] mymf;
	private Integer[] numberHeld;

	public MututalFundPortfolio(MutualFund[] mutualFunds, Integer[] numberHeld){
		this.mymf = mutualFunds;
		this.numberHeld = numberHeld;
		calculateCurrentValue();
	}
	
	// toString method
	public String toString() {
		String outputString = "Mutual Funds Owned:\n";
 		for (int i= 0; i < mymf.length; i++){
 			outputString += "\t"+mymf[i].name+": "+ numberHeld[i] + "\n";
 		}
 		return outputString;
	}

	@Override
	public void calculateCurrentValue() {
		double value = 0.0;
		for(int i=0; i < mymf.length; i++) {
			value += mymf[i].currentValue * numberHeld[i];
		}
		this.currentValue = value;
	}

	public MutualFund[] getMutualFunds() {
		return mymf;
	}

	public Integer[] getNumberHeld() {
		return numberHeld;
	}
	
	
}