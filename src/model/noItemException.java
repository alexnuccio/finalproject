package model;

public class noItemException extends RuntimeException{

	private String message = "You don't have any of those!";
	
	public noItemException() {
		super();
	}
	
	public String getMessage() {
		return message;
	}
	
}
