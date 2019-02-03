/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * InvalidAssetNameException 
 * 
 */

public class InvalidAssetNameException extends Exception{
	
	private String message = "";
	
	public InvalidAssetNameException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "INVALID_ASSET_NAME_EXCEPTION : " + message;
	}
}
