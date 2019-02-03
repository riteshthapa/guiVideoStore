/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * NotEnoughCashInHandToBuyException 
 * 
 */
public class NotEnoughCashInHandToBuyException extends Exception {
	
	private String message = "";
	
	public NotEnoughCashInHandToBuyException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "NOT_ENOUGH_CASH_IN_HAND_TO_BUY_EXCEPTION : " + message;
	}
}