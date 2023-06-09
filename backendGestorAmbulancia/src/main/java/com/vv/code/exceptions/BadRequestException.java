package com.vv.code.exceptions;

/**
 * 
 * @author Natanael Muñoz
 * @version 1.0
 * Date: 09/06/2023
 */
public class BadRequestException extends RuntimeException{
	
	private String code;
	private String message;
	
	public BadRequestException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
