package org.rahul.webdev.messenger.exception;

public class DataNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2250132728203929097L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
