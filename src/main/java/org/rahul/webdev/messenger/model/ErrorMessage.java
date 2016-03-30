package org.rahul.webdev.messenger.model;

public class ErrorMessage {
	private String message;
	private int code;
	private String doc;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String message, int code, String doc) {
		this.message = message;
		this.code = code;
		this.doc = doc;
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
}
