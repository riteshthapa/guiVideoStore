/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * NotEnoughAssetToSellException
 * 
 */

public class NotEnoughAssetToSellException extends Exception {
	
	private String message = "";
	
	public NotEnoughAssetToSellException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "NOT_ENOUGH_ASSET_TO_SELL_EXCEPTION : " + message;
	}
}
