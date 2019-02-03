/*
 * Ritesh Thapa 
 * CS 250
 * Assignment2C
 * 
 * InvalidCustomerIDException 
 * 
 */

public class InvalidCustomerIDException extends Exception {
	
	private String message = "";
	
	public InvalidCustomerIDException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "INVALID_CUSTOMER_ID_EXCEPTION : " + message;
	}
}
