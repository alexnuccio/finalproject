package model;

/**
 * noItemException
 * 
 * Exception thrown when a player tries to use an item they do not have.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class noItemException extends RuntimeException{

	private String message = "You don't have any of those!";
	
	/**
	 * noItemException
	 * 
	 * Constructor for the exception
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public noItemException() {
		super();
	}
	
	/**
	 * getMessage
	 * 
	 * Returns the exception message
	 * 
	 * @return String
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public String getMessage() {
		return message;
	}
	
}
