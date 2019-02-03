/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * NotEnoughAssetToBuyException
 * 
 */



public class NotEnoughAssetToBuyException extends Exception {
	
	private String message = "";
	
	public NotEnoughAssetToBuyException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "NOT_ENOUGH_ASSET_TO_BUY_EXCEPTION : " + message;
	}
}
